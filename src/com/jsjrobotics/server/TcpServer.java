package com.jsjrobotics.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    private final int mPortNumber;
    private final ServerSocket mSocket;
    private BufferedReader inputReader;
    private Socket clientSocket = null;
    private final ConnectedListener mListener;
    private BufferedOutputStream outputStream;

    private final Thread serverThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                clientSocket = mSocket.accept();
                System.out.println("Client connected");
                outputStream = new BufferedOutputStream(clientSocket.getOutputStream(),25344);
                inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                mListener.connectionInitiated();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });

    public TcpServer(int port,ConnectedListener listener) throws IOException {
        mPortNumber = port;
        mSocket = new ServerSocket(mPortNumber);
        mListener = listener;
    }

    private boolean isConnected(){
        if(outputStream != null && inputReader != null && clientSocket != null && clientSocket.isConnected()){
            return true;
        }
        return false;
    }

    public void start(){
        serverThread.start();
    }
    public void transmit(int[] buffer,int offset,int bytesToWrite){
        if(isConnected()){
            try {
                for(int index = offset; index < offset+bytesToWrite; index++) {
                    outputStream.write(buffer[index]);
                }
                System.out.println("Wrote "+bytesToWrite);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
