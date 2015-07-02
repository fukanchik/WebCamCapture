package com.jsjrobotics.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UiBuilder {
    private static int DEFAULT_FRAME_WIDTH = 500;
    private static int DEFAULT_FRAME_HEIGHT = 500;
    private static Component mainJPanel;
    private static ActionListener tuningChamberSelectedListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFrame presentation = getClosableFrame();
            presentation.setTitle("Tuning Chamber");
            presentation.setVisible(true);
        }
    };
    private static ActionListener presentationPortalSelectedListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFrame presentation = getClosableFrame();
            presentation.setTitle("Presentation Portal");
            presentation.setVisible(true);
        }
    };

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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return frame;
    }

    private static JButton buildMainButton(){
        JButton button = new JButton();
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return button;

    }
    private static Component getMainJPanel() {
        JButton button1 = buildMainButton();
        JButton button2 = buildMainButton();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(button1);
        panel.add(button2);
        try {
            BufferedImage selection1 = ImageIO.read(new File("main1.jpg"));
            BufferedImage selection2 = ImageIO.read(new File("main2.png"));
            attachButtonIcon(selection1,button1);
            attachButtonIcon(selection2,button2);
            attachOnClickListener(1,button1);
            attachOnClickListener(1,button2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return panel;
    }

    private static void attachOnClickListener(int buttonIndex, JButton button) {
        switch (buttonIndex){
            case 1:
                button.addActionListener(tuningChamberSelectedListener);
                break;
            case 2:
                button.addActionListener(presentationPortalSelectedListener);
                break;
        }
    }

    private static void attachButtonIcon(BufferedImage selection1, JButton button1) {
        ImageIcon image = new ImageIcon(selection1);
        button1.setAlignmentX(.5f);
        button1.setAlignmentY(.5f);
        button1.setIcon(image);
    }
}
