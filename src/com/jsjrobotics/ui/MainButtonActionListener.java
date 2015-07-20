package com.jsjrobotics.ui;

import com.jsjrobotics.client.TcpClientMain;
import com.jsjrobotics.lasers.LaserControlMain;
import com.jsjrobotics.server.ServerMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public abstract class MainButtonActionListener implements ActionListener {
    protected final Component parent;
    public MainButtonActionListener(Component parent){
        this.parent = parent;
    }

    public Component getParent(){
        return parent;
    }

    protected ActionListener startLaserSwitchesActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LaserControlMain().start();
        }
    };

    protected ActionListener startWebcamServerActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                new ServerMain().start();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    };

    protected ActionListener startWebcamClientActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel(new GridLayout(2, 3));
            JLabel camera1 = new JLabel();
            JLabel camera2 = new JLabel();
            JLabel camera3 = new JLabel();
            JLabel camera4 = new JLabel();
            JLabel camera5 = new JLabel();


            panel.add(camera1);
            panel.add(camera2);
            panel.add(camera3);
            panel.add(camera4);
            panel.add(camera5);


            JFrame frame = UiBuilder.buildClientFrame();
            frame.getContentPane().add(panel);
            frame.setVisible(true);

            new TcpClientMain(4445,camera1).start();
            new TcpClientMain(4446,camera2).start();
            new TcpClientMain(4447,camera3).start();
            new TcpClientMain(4448,camera4).start();
            new TcpClientMain(4449,camera5).start();


        }
    };

}
