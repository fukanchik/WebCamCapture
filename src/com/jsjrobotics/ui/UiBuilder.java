package com.jsjrobotics.ui;

import javax.swing.*;

public class UiBuilder {
    public static JFrame buildClientFrame() {
        JFrame frame = new JFrame();
        frame.setSize(176,144);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    public static JFrame buildServerFrame(){
        JFrame frame = new JFrame();
        frame.setSize(176,144);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }
}
