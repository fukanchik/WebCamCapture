package com.jsjrobotics;

import com.jsjrobotics.server.ConnectedListener;
import com.jsjrobotics.server.TcpServer;
import com.jsjrobotics.server.TrasmitController;

import java.io.IOException;
import java.net.Socket;

public class Main {
    private static int controllerInstances = 0;
    private static TcpServer server;

    private static TrasmitController trasmitController;

    private static ConnectedListener listener = new ConnectedListener() {
        @Override
        public void connectionInitiated(int socketIndex) {
            if(trasmitController != null && !trasmitController.isAlive()) {
                trasmitController.start();
            }
            else {
                trasmitController = new TrasmitController("TransmitController"+controllerInstances, server);
                controllerInstances += 1;
                trasmitController.start();
            }
        }
    };
    public static void main(String[] args) throws IOException {
        server = new TcpServer(4445,listener);
        trasmitController = new TrasmitController("TransmitController"+controllerInstances, server);
        controllerInstances+=1;
        server.start();
    }
}
