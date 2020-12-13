package com.zy.tank.entity;

import com.zy.tank.TankFrame;
import com.zy.tank.tankenum.Direction;
import com.zy.tank.tankenum.Group;
import com.zy.tank.manager.ResourceMgr;

import java.awt.*;
import java.util.Random;

public class Tank extends AbstraceGameObject {
    private int x;
    private int y;
    private int oldX;
    private int oldY;
    private int width;
    private int height;
    private Direction direction;
    private static final int SPEED = 2;
    private boolean moving = true;
    private Group group;
    private boolean isAlive = true;

    public Tank(int x, int y, Direction direction,Group group) {
        this.x = x;
        this.y = y;
        oldX = x;
        oldY = y;
        width = ResourceMgr.goodTankU.getWidth();
        height = ResourceMgr.goodTankU.getHeight();
        this.direction = direction;
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!isAlive) {
            return;
        }
        if (!moving) {
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
        boundsCheck();
        Random random = new Random();
        if (random.nextInt(100) > 95) {
            direction = Direction.randmDirection();
            fire();
        }
    }

    private void fire() {
        int bx = x + ResourceMgr.goodTankU.getWidth()/2 - ResourceMgr.bulletU.getWidth()/ 2;
        int by = y + ResourceMgr.goodTankU.getHeight()/2 - ResourceMgr.bulletU.getHeight()/2;
        TankFrame.INSTANCE.add(new Bullt(bx, by, direction, Group.BAD));
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x + width > TankFrame.GAME_WIDTH || y + height > TankFrame.GAME_HEIGHT) {
            back();
        }
    }

    private void back() {
        this.x = oldX;
        this.y = oldY;
    }

    private void stop() {
        this.moving = false;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
