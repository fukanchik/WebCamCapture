package com.jsjrobotics.server;

import com.github.sarxos.webcam.Webcam;

import java.util.ArrayList;
import java.util.List;

public class TrasmitController extends Thread {
    private final TcpServer server;
    private final CameraThread camera1;
    private final CameraThread camera2;
    private final CameraThread camera3;
    private final CameraThread camera4;


    private final List<CameraThread> cameraThreads = new ArrayList<>();

    public TrasmitController(String name, TcpServer server){
        super(name);
        this.server = server;
        List<Webcam> webcams = Webcam.getWebcams();
        camera1 = webcams.size() >= 1 ? new CameraThread(1,this,webcams.get(0)) : null;
        camera2 = webcams.size() >= 2 ? new CameraThread(2,this,webcams.get(1)) : null;
        camera3 = webcams.size() >= 3 ? new CameraThread(3,this,webcams.get(2)) : null;
        camera4 = webcams.size() >= 4 ? new CameraThread(4,this,webcams.get(3)) : null;
    }

    @Override
    public void run() {
        if(camera1 != null && camera1.readyToStart()){
            camera1.start();
        }
        if(camera2 != null && camera2.readyToStart()){
            camera2.start();
        }
        if(camera3 != null && camera3.readyToStart()){
            camera3.start();
        }
        if(camera4 != null && camera4.readyToStart()){
            camera4.start();
        }

    }

    public void transmit(int threadNumber, int[] buffer, int offset, int transmitLength) {
        int[] data = prependCameraInformation(threadNumber,buffer,offset,transmitLength);
        server.transmit(data,0,data.length);
    }

    private int[] prependCameraInformation(int threadNumber, int[] buffer, int offset, int transmitLength) {
        int[] result = new int[transmitLength];
        for(int index = 0; index < transmitLength; index++){
            result[index] = buffer[offset+index];
        }
        return result;
    }
}
