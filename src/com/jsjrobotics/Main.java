package com.jsjrobotics;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Main {
    private static TcpServer server;

    private static Thread transmitThread = new Thread(new Runnable() {
        @Override
        public void run() {
            Webcam webcam = Webcam.getDefault();
            webcam.open();
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
                System.out.println("Width / Height  -  "+image.getWidth() + " - "+image.getHeight());
                server.transmit(buffer,0,width*height);
            }

        }
    });

    private static ConnectedListener listener = new ConnectedListener() {
        @Override
        public void connectionInitiated() {
            transmitThread.start();
        }
    };
    public static void main(String[] args) throws IOException {
        server = new TcpServer(4445,listener);
    }
}
