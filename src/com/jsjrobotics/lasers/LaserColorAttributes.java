package com.jsjrobotics.lasers;

import javax.swing.*;
import java.awt.*;

public class LaserColorAttributes {
    public final int columns;
    public final int dutyCycle;
    public final int velocity;

    public LaserColorAttributes(int columns, int dutyCycle, int velocity){
        this.columns = columns;
        this.dutyCycle = dutyCycle;
        this.velocity = velocity;
    }

    public JPanel getJPanel(){
        JPanel panel = new JPanel(new GridLayout(1, 3));
        JLabel columnLabel = new JLabel(String.valueOf(columns));
        JLabel dutyCycleLabel = new JLabel(String.valueOf(dutyCycle));
        JLabel velocityLabel = new JLabel(String.valueOf(velocity));
        panel.add(columnLabel);
        panel.add(dutyCycleLabel);
        panel.add(velocityLabel);
        return panel;
    }
}
