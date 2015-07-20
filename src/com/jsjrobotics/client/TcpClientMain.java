package com.jsjrobotics.client;

import com.jsjrobotics.ui.UiBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class TcpClientMain {
    private final JLabel jLabel;
    private final int port;

    public TcpClientMain(int port, JLabel label){
        this.jLabel = label;
        this.port = port;
    }
    public  void start(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                startClient(port);
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
    private  void startClient(int port){
        try{
            InetAddress address = InetAddress.getByName("192.168.0.211");
            Socket socket = new Socket(address,port);
            InputStream inputStream = socket.getInputStream();
            byte[] buffer1 = new byte[25344*4];
            int buffer1Read = 0;
            while (true){
                int read = inputStream.read(buffer1,buffer1Read,25344*4-buffer1Read);
                if(read != -1){
                    buffer1Read+= read;
                    if(buffer1Read == 25344*4){
                        System.out.println("Read image "+System.currentTimeMillis());
                        drawImage(buffer1);
                        buffer1Read = 0;
                    }
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private  void drawImage(final byte[] buffer1) {
        int width = 176;
        int height = 144;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        ByteBuffer buffer = ByteBuffer.wrap(buffer1);
        int[] rgbArray = new int[width*height];
        buffer.asIntBuffer().get(rgbArray);
        image.setRGB(0,0,width,height,rgbArray,0,width);
        BufferedImage after = new BufferedImage(2*width, 2*height, BufferedImage.TYPE_3BYTE_BGR);
        AffineTransform at = new AffineTransform();
        at.scale(2.0, 2.0);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(image, after);
        jLabel.setIcon(new ImageIcon(after));

    }
}
