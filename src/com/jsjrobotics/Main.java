package com.jsjrobotics;

import com.jsjrobotics.ui.UiBuilder;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame frame = UiBuilder.buildMainSelectionFrame();
        frame.setVisible(true);
    }
}
