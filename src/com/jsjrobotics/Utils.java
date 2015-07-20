package com.jsjrobotics;

import javax.swing.*;
import java.awt.image.BufferedImage;

public final class Utils {
    private Utils(){};

    public static void attachButtonIcon(BufferedImage selection1, JButton button1) {
        ImageIcon image = new ImageIcon(selection1);
        button1.setAlignmentX(.5f);
        button1.setAlignmentY(.5f);
        button1.setIcon(image);
    }
}
