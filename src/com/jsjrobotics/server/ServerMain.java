package com.jsjrobotics.server;

import java.io.IOException;
import java.util.ArrayList;

public class ServerMain {
    private  int controllerInstances = 0;
    private ArrayList<TcpServer> servers = new ArrayList<>();

    private  TrasmitController trasmitController;

    private  ConnectedListener listener1 = new ConnectedListener() {
        @Override
        public void connectionInitiated(int socketIndex) {
            launchTransmitController();
        }
    };

    private  ConnectedListener listener2 = new ConnectedListener() {
        @Override
        public void connectionInitiated(int socketIndex) {
            launchTransmitController();
        }
    };

    public  void start() throws IOException {
        TcpServer server1 = new TcpServer(4445, listener1);
        TcpServer server2 = new TcpServer(4446, listener2);
        servers.add(server1);
        servers.add(server2);
        server1.start();
        server2.start();
        launchTransmitController();
    }

    private  void launchTransmitController(){
        if(trasmitController == null) {
            trasmitController = new TrasmitController("TransmitController"+controllerInstances, servers);
            controllerInstances += 1;
            trasmitController.start();
        }
        else if(!trasmitController.isAlive()){
            trasmitController.start();
        }
    }
}
