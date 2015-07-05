package com.jsjrobotics.ui;

import com.jsjrobotics.client.TcpClient;
import com.jsjrobotics.server.ServerMain;

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
            new TcpClient().start();
        }
    };

}
