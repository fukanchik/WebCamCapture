package com.jsjrobotics.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UiBuilder {
    private static int DEFAULT_FRAME_WIDTH = 500;
    private static int DEFAULT_FRAME_HEIGHT = 500;
    private static Component mainJPanel;

    public static JFrame buildClientFrame() {
        JFrame frame = getClosableFrame();
        frame.setSize(176,144);
        return frame;
    }

    public static JFrame buildServerFrame(){
        JFrame frame = getClosableFrame();
        frame.setSize(176,144);
        return frame;
    }

    public static JFrame buildMainSelectionFrame() {
        JFrame frame = getClosableFrame();
        frame.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
        frame.getContentPane().add(getMainJPanel());
        return frame;
    }

    private static JFrame getClosableFrame(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    private static Component getMainJPanel() {
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(jLabel1);
        panel.add(jLabel2);
        try {
            BufferedImage selection1 = ImageIO.read(new File("main1.jpg"));
            BufferedImage selection2 = ImageIO.read(new File("main2.png"));
            jLabel1.setIcon(new ImageIcon(selection1));
            jLabel2.setIcon(new ImageIcon(selection2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return panel;
    }
}
