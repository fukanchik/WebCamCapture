package com.jsjrobotics.lasers;

import javax.swing.*;
import java.awt.*;

public class PatchRowAttributes {
    public final int patchNumber;
    public final LaserColorAttributes redAttributes;
    public final LaserColorAttributes greenAttributes;
    public final LaserColorAttributes blueAttributes;

    private PatchRowAttributes(int patchNumber,LaserColorAttributes red, LaserColorAttributes green, LaserColorAttributes blue){
        this.patchNumber = patchNumber;
        this.redAttributes = red;
        this.greenAttributes = green;
        this.blueAttributes = blue;
    }

    public static JPanel getTitleBar(){
        JPanel titleBar = new JPanel(new GridLayout(1,10));
        titleBar.add(new JLabel("Patch #"));
        titleBar.add(new JLabel("Red Columns (0 - 15)"));
        titleBar.add(new JLabel("Red Duty Cycle (0 - 100)"));
        titleBar.add(new JLabel("Red Velocity (-63 - 63)"));
        titleBar.add(new JLabel("Green Columns"));
        titleBar.add(new JLabel("Green Duty Cycle"));
        titleBar.add(new JLabel("Green Velocity"));
        titleBar.add(new JLabel("Blue Columns"));
        titleBar.add(new JLabel("Blue Duty Cycle"));
        titleBar.add(new JLabel("Blue Velocity"));
        return titleBar;
    }

    public JPanel getJPanel(){
        JPanel panel = new JPanel(new GridLayout(1, 4));
        JLabel patch = new JLabel(String.valueOf(patchNumber));
        panel.add(patch);
        panel.add(redAttributes.getJPanel());
        panel.add(greenAttributes.getJPanel());
        panel.add(blueAttributes.getJPanel());
        return panel;
    }

    public static class Builder{
        private int patchNumber = -1;
        private int redColumns = 0;
        private int redDutyCycle = 0;
        private int redVelocity = 0;
        private int greenColumns = 0;
        private int greenDutyCycle = 0;
        private int greenVelocity = 0;
        private int blueColumns = 0;
        private int blueVelocity = 0;
        private int blueDutyCycle = 0;

        public Builder(){}

        public Builder(Builder prepopulate){
            patchNumber = prepopulate.patchNumber;
            redColumns = prepopulate.redColumns;
            redDutyCycle = prepopulate.redDutyCycle;
            redVelocity = prepopulate.redVelocity;
            greenColumns = prepopulate.greenColumns;
            greenDutyCycle = prepopulate.greenDutyCycle;
            greenVelocity = prepopulate.greenVelocity;
            blueColumns = prepopulate.blueColumns;
            blueDutyCycle = prepopulate.blueDutyCycle;
            blueVelocity = prepopulate.blueVelocity;
        }

        public Builder setPatchNumber(int patchNumber) {
            this.patchNumber = patchNumber;
            return this;
        }

        public Builder setRedColumns(int redColumns) {
            this.redColumns = redColumns;
            return this;
        }

        public Builder setBlueVelocity(int blueVelocity) {
            this.blueVelocity = blueVelocity;
            return this;
        }

        public Builder setBlueDutyCycle(int blueDutyCycle) {
            this.blueDutyCycle = blueDutyCycle;
            return this;
        }

        public Builder setRedDutyCycle(int redDutyCycle) {
            this.redDutyCycle = redDutyCycle;
            return this;
        }

        public Builder setRedVelocity(int redVelocity) {
            this.redVelocity = redVelocity;
            return this;
        }

        public Builder setGreenColumns(int greenColumns) {
            this.greenColumns = greenColumns;
            return this;
        }

        public Builder setGreenDutyCycle(int greenDutyCycle) {
            this.greenDutyCycle = greenDutyCycle;
            return this;
        }

        public Builder setGreenVelocity(int greenVelocity) {
            this.greenVelocity = greenVelocity;
            return this;
        }

        public Builder setBlueColumns(int blueColumns) {
            this.blueColumns = blueColumns;
            return this;
        }

        public PatchRowAttributes build(){
            LaserColorAttributes red = new LaserColorAttributes(redColumns,redDutyCycle,redVelocity);
            LaserColorAttributes blue = new LaserColorAttributes(blueColumns,blueDutyCycle,blueVelocity);
            LaserColorAttributes green = new LaserColorAttributes(greenColumns,greenDutyCycle,greenVelocity);

            return new PatchRowAttributes(patchNumber,red,green,blue);
        }

    }
}
