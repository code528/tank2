package com.zy.tank;

import com.zy.tank.entity.Tank;
import com.zy.tank.tankenum.Direction;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankFrame extends Frame {

    private Tank tank;
    private Tank enemy;

    public TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(800, 600);
        tank = new Tank(100, 100, Direction.R);
        enemy = new Tank(200, 200, Direction.D);
        // 使用了观察者模式，观看坦克第一期
        this.addKeyListener(new TankListener());
    }

    @Override
    public void paint(Graphics g) {
        tank.paint(g);
        enemy.paint(g);
    }

    public class TankListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            tank.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            tank.keyReleased(e);
        }
    }
}
