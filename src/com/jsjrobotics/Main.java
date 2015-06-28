package com.jsjrobotics;

import com.github.sarxos.webcam.Webcam;
import com.jsjrobotics.server.CameraThread;
import com.jsjrobotics.server.ConnectedListener;
import com.jsjrobotics.server.TcpServer;
import com.jsjrobotics.server.TrasmitController;

import java.io.IOException;
import java.util.List;

public class Main {
    /**
     * TODO: Single instance is returned from server on connection, only 1 client can connect
     */
    private static TcpServer server;

    private static TrasmitController trasmitController;

    private static ConnectedListener listener = new ConnectedListener() {
        @Override
        public void connectionInitiated() {
            trasmitController.start();
        }
    };
    public static void main(String[] args) throws IOException {
        server = new TcpServer(4445,listener);
        trasmitController = new TrasmitController("TransmitController", server);
        server.start();
    }
}
