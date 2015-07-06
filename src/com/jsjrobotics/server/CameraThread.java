package com.jsjrobotics.server;

import com.github.sarxos.webcam.Webcam;
import com.jsjrobotics.server.TcpServer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CameraThread extends Thread {
    private final Webcam webcam;
    private final TrasmitController controller;
    private final boolean readyToStart;
    private final int threadNumber;
    private final TcpServer server;
    public CameraThread(int threadNumber,TrasmitController controller,Webcam webcam, TcpServer server){
        super("CameraThread:"+threadNumber);
        this.threadNumber = threadNumber;
        this.webcam = webcam;
        this.controller = controller;
        this.server = server;
        readyToStart = controller != null && webcam != null && server!= null;
    }

    @Override
    public void run(){
        System.out.println(getName() + " Started");
        webcam.open();
        Dimension[] customSizes = webcam.getCustomViewSizes();
        for(int index = 0; index < customSizes.length; index++){
            System.out.println("Available Size: "+customSizes[0]);
        }
        int[] buffer = null;
        while(true) {
            BufferedImage image = webcam.getImage();
            int width = image.getWidth();
            int height = image.getHeight();
            int minBufferSize = width * height;
            if( buffer == null || buffer.length < minBufferSize){
                buffer = new int[minBufferSize];
            }
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), buffer, 0, image.getWidth());
            //System.out.println("Width / Height  -  "+image.getWidth() + " - "+image.getHeight());
            controller.transmit(server,threadNumber,buffer,0,width*height);
        }
    }

    public boolean readyToStart() {
        return readyToStart;
    }

    public TcpServer getServer(){
        return server;
    }
}
