package com.jsjrobotics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class TcpClient {
    private static JFrame frame;

    public static void main(String[] args){
        drawJFrame();
        try{
            InetAddress address = InetAddress.getByName("192.168.1.105");
            int port = 4445;
            Socket socket = new Socket(address,port);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            byte[] buffer1 = new byte[25344*4];
            byte[] buffer2 = new byte[25344*4];
            int buffer1Read = 0;
            int buffer2Read = 0;
            boolean usingBuffer1 = true;
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

    private static void drawJFrame() {
        frame = new JFrame();
        frame.setSize(176,144);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private static void drawImage(final byte[] buffer1) {
        BufferedImage image = new BufferedImage(176,144,BufferedImage.TYPE_INT_RGB);
        ByteBuffer buffer = ByteBuffer.wrap(buffer1);
        int[] rgbArray = new int[25344];
        buffer.asIntBuffer().get(rgbArray);
        image.setRGB(0,0,176,144,rgbArray,0,176);
        JLabel jLabel = new JLabel(new ImageIcon(image));
        JPanel panel = new JPanel(new GridLayout(0,2));
        panel.add(jLabel);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }
}
