package com.jsjrobotics.server;

import com.github.sarxos.webcam.Webcam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
    private final int firstPort = 4445;
    private final List<Webcam> webcams;
    private int controllerInstances = 0;
    private ArrayList<TcpServer> servers = new ArrayList<>();

    private  TrasmitController trasmitController;

    public ServerMain(){
        webcams = Webcam.getWebcams();
    }
    private  ConnectedListener listener = new ConnectedListener() {
        @Override
        public void connectionInitiated(int socketIndex) {
            launchTransmitController();
        }
    };


    public  void start() throws IOException {
        List<Webcam> webcams = Webcam.getWebcams();
        for(int index=0; index<webcams.size(); index++){
            TcpServer server = new TcpServer(firstPort+index, listener);
            server.start();
            servers.add(server);

        }
        launchTransmitController();
    }

    private  void launchTransmitController(){
        if(trasmitController == null) {
            trasmitController = new TrasmitController("TransmitController"+controllerInstances, servers,webcams);
            controllerInstances += 1;
            trasmitController.start();
        }
        else if(!trasmitController.isAlive()){
            trasmitController.start();
        }
    }
}
