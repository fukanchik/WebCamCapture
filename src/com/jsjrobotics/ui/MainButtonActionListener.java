package com.jsjrobotics.ui;

import com.jsjrobotics.client.TcpClient;
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
            JPanel panel = new JPanel(new GridLayout(0, 2));
            JLabel camera1 = new JLabel();
            JLabel camera2 = new JLabel();

            panel.add(camera1);
            panel.add(camera2);

            JFrame frame = UiBuilder.buildClientFrame();
            frame.getContentPane().add(panel);
            frame.setVisible(true);

            new TcpClient(4445,camera1).start();
            new TcpClient(4446,camera2).start();
        }
    };

}
