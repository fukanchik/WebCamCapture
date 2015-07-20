package com.jsjrobotics.lasers;

import com.jsjrobotics.ui.UiBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LaserControlMain {
    public void start() {
        PatchRowAttributes.Builder builder = new PatchRowAttributes.Builder();
        PatchRowAttributes built = builder.setRedColumns(3).setRedDutyCycle(100).setRedVelocity(45).build();
        JPanel panel = new JPanel(new GridLayout(2, 1));

        
        panel.add(PatchRowAttributes.getTitleBar());
        panel.add(built.getJPanel());

        JFrame frame = UiBuilder.buildLaserControlFrame();
        frame.getContentPane().add(panel);
        frame.setVisible(true);




    }
}
