package com.zy.tank;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        TankFrame.INSTANCE.setVisible(true);

        for (;;) {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TankFrame.INSTANCE.repaint();
        }
    }
}
