package com.zy.tank.entity;

import com.zy.tank.tankenum.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x;
    private int y;
    private Direction direction;
    // left标识
    private boolean bL;
    // right标识
    private boolean bR;
    // up标识
    private boolean bU;
    // down标识
    private boolean bD;
    private static final int SPEED = 5;

    public Tank(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
        move();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
        }
        setTankDriection();
    }

    private void setTankDriection() {
        if (!bL && !bR && !bD && !bU) {
            direction = Direction.STOP;
        }
        if (bL && !bR && !bD && !bU) {
            direction = Direction.L;
        }
        if (!bL && bR && !bD && !bU) {
            direction = Direction.R;
        }
        if (!bL && !bR && bD && !bU) {
            direction = Direction.D;
        }
        if (!bL && !bR && !bD && bU) {
            direction = Direction.U;
        }
    }

    private void move() {
        switch (direction) {
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case D:
                y += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
        }
        setTankDriection();
    }
}
