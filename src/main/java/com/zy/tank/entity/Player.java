package com.zy.tank.entity;

import com.zy.tank.TankFrame;
import com.zy.tank.tankenum.Direction;
import com.zy.tank.tankenum.Group;
import com.zy.tank.util.ResourceMgr;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player {
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
    private boolean moving = false;
    private Group group;
    private boolean isAlive = true;

    public Player(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!isAlive) {
            return;
        }

        switch (direction) {
            case L:
                g.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
        }

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
            moving = false;
        } else {
            moving = true;
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

    }

    private void move() {
        if (!moving) {
            return;
        }
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
            case KeyEvent.VK_CONTROL:
                fire();
                break;
        }
        setTankDriection();
    }

    private void fire() {
        int bx = x + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/ 2;
        int by = y + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;
        TankFrame.INSTANCE.add(new Bullt(bx, by, direction, Group.GOOD));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void die() {
        this.isAlive = false;
    }
}
