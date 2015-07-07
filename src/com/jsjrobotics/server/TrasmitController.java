package com.jsjrobotics.server;

import com.github.sarxos.webcam.Webcam;

import java.util.ArrayList;
import java.util.List;

public class TrasmitController extends Thread {

    private final List<CameraThread> cameraThreads = new ArrayList<>();

    public TrasmitController(String name, ArrayList<TcpServer> serverList,List<Webcam> webcams){
        super(name);
        int max = serverList.size();
        if(max < webcams.size()){
            max = webcams.size();
        }
        for(int index = 0; index < max; index ++){
            CameraThread camera = new CameraThread(1,this,webcams.get(index),serverList.get(index));
            cameraThreads.add(camera);
        }
    }

    @Override
    public void run() {
        for(int index =0; index < cameraThreads.size(); index++){
            CameraThread camera = cameraThreads.get(index);
            if(camera.readyToStart()){
                camera.start();
            }
        }

    }

    public void transmit(TcpServer server,int threadNumber, int[] buffer, int offset, int transmitLength) {
        int[] data = prependCameraInformation(threadNumber,buffer,offset,transmitLength);
        server.transmit(data,0,data.length);
    }

    public int[] prependCameraInformation(int threadNumber, int[] buffer, int offset, int transmitLength) {
        int[] result = new int[transmitLength];
        for(int index = 0; index < transmitLength; index++){
            result[index] = buffer[offset+index];
        }
        return result;
    }
}
