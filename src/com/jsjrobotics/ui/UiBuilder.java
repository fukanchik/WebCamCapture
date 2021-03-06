package com.jsjrobotics.ui;

import com.jsjrobotics.Utils;

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


    public static JFrame buildLaserControlFrame() {
        JFrame frame = getClosableFrame();
        frame.setSize(176, 144);
        return frame;
    }

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
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            BufferedImage selection1 = ImageIO.read(new File("presentation_portal.png"));
            BufferedImage selection2 = ImageIO.read(new File("tuning_chamber.png"));
            Utils.attachButtonIcon(selection1, button1);
            Utils.attachButtonIcon(selection2, button2);
            attachOnClickListener(1,button1);
            attachOnClickListener(2,button2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return panel;
    }

    private static void attachOnClickListener(int buttonIndex, JButton button) {
        switch (buttonIndex){
            case 1:
                button.addActionListener(getPresentationPortalSelectedListener(button));
                break;
            case 2:
                button.addActionListener(getTuningChamberSelectedListener(button));
                break;
        }
    }

    private static ActionListener getTuningChamberSelectedListener(JButton button) {
        MainButtonActionListener presentationPortalSelectedListener = new MainButtonActionListener(button) {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JPopupMenu popup = new JPopupMenu();
                JMenuItem menuItem = new JMenuItem("Not Implemented");
                popup.add(menuItem);
                popup.show(parent,parent.getX(),parent.getY());
            }
        };
        return presentationPortalSelectedListener;
    }

    private static ActionListener getPresentationPortalSelectedListener(JButton button) {
        MainButtonActionListener tuningChamberSelectedListener = new MainButtonActionListener(button) {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JPopupMenu popup = new JPopupMenu();
                JMenuItem menuItem = new JMenuItem("Laser Switches");
                menuItem.addActionListener(startLaserSwitchesActionListener);
                popup.add(menuItem);
                menuItem = new JMenuItem("Webcam Server");
                menuItem.addActionListener(startWebcamServerActionListener);
                popup.add(menuItem);
                menuItem = new JMenuItem("Webcam Client");
                menuItem.addActionListener(startWebcamClientActionListener);
                popup.add(menuItem);
                popup.show(parent,parent.getX(),parent.getY());
            }
        };
        return tuningChamberSelectedListener;
    }



}
