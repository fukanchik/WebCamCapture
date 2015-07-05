package com.jsjrobotics.server;

import com.jsjrobotics.server.ConnectedListener;
import com.jsjrobotics.server.TcpServer;
import com.jsjrobotics.server.TrasmitController;

import java.io.IOException;
import java.net.Socket;

public class ServerMain {
    private  int controllerInstances = 0;
    private  TcpServer server;

    private  TrasmitController trasmitController;

    private  ConnectedListener listener = new ConnectedListener() {
        @Override
        public void connectionInitiated(int socketIndex) {
            launchTransmitController(server);
        }
    };

    public  void start() throws IOException {
        server = new TcpServer(4445,listener);
        launchTransmitController(server);
        server.start();
    }

    private  void launchTransmitController(TcpServer server){
        if(trasmitController == null) {
            trasmitController = new TrasmitController("TransmitController"+controllerInstances, server);
            controllerInstances += 1;
            trasmitController.start();
        }
        else if(!trasmitController.isAlive()){
            trasmitController.start();
        }
    }
}
