package com.zy.tank.entity;

import com.zy.tank.TankFrame;
import com.zy.tank.tankenum.Direction;
import com.zy.tank.tankenum.Group;
import com.zy.tank.util.ResourceMgr;

import java.awt.*;

public class Bullt {
    private int x;
    private int y;
    private Direction direction;
    private Group group;
    private boolean isAlive = true;
    private static final int SPEED = 10;

    public Bullt(int x, int y, Direction direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
    }

    public void paint(Graphics g) {
        switch (direction) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        move();
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
        boundsCheck();
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            setAlive(false);
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void collideWithTank(Tank tank) {
        if (!tank.isAlive()) {
            return;
        }
        if (tank.getGroup() == this.group) {
            return;
        }
        Rectangle bulltRectangle = new Rectangle(x, y, ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
        Rectangle tankRectangle = new Rectangle(tank.getX(), tank.getY(), ResourceMgr.badTankU.getWidth(), ResourceMgr.badTankU.getHeight());
        if (bulltRectangle.intersects(tankRectangle)) {
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.isAlive = false;
    }
}
