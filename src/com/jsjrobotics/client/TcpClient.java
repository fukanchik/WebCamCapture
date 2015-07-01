package com.jsjrobotics.client;

import com.jsjrobotics.ui.UiBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class TcpClient {
    private static JFrame frame;
    private static JLabel jLabel;
    private static JPanel panel;

    public static void main(String[] args){
        jLabel = new JLabel();
        panel = new JPanel(new GridLayout(0, 2));
        panel.add(jLabel);
        frame = UiBuilder.buildClientFrame();
        frame.getContentPane().add(panel);
        frame.setVisible(true);

        try{
            InetAddress address = InetAddress.getByName("10.89.196.77");
            int port = 4445;
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
        finally {
            frame.dispose();
        }
    }




    private static void drawImage(final byte[] buffer1) {
        BufferedImage image = new BufferedImage(176,144,BufferedImage.TYPE_INT_RGB);
        ByteBuffer buffer = ByteBuffer.wrap(buffer1);
        int[] rgbArray = new int[25344];
        buffer.asIntBuffer().get(rgbArray);
        image.setRGB(0,0,176,144,rgbArray,0,176);
        jLabel.setIcon(new ImageIcon(image));

    }
}
