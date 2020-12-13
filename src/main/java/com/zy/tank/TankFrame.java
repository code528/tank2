package com.zy.tank;

import com.zy.tank.entity.Explode;
import com.zy.tank.entity.AbstraceGameObject;
import com.zy.tank.entity.Bullt;
import com.zy.tank.entity.Player;
import com.zy.tank.entity.Tank;
import com.zy.tank.entity.Wall;
import com.zy.tank.manager.PropertyMgr;
import com.zy.tank.tankenum.Direction;
import com.zy.tank.tankenum.Group;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    public static final TankFrame INSTANCE = new TankFrame();
    private Player player;
    private List<AbstraceGameObject> gameObjects;
    private TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        initGameObjects();

        // 使用了观察者模式，观看坦克第一期
        this.addKeyListener(new TankListener());
    }

    private void initGameObjects() {
        player = new Player(100, 100, Direction.R, Group.GOOD);
        gameObjects = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(PropertyMgr.getPropertyValue("initTankCount")); i++) {
            gameObjects.add(new Tank(100 + 50 * i, 200, Direction.D, Group.BAD));
        }
        gameObjects.add(new Wall(300, 200, 200, 30));
    }

    @Override
    public void paint(Graphics g) {
        Color c = g .getColor();
        g.setColor(Color.WHITE);
        g.setColor(c);
        player.paint(g);

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }


//        for (int i = 0; i < enemies.size(); i++) {
//            if (!enemies.get(i).isAlive()) {
//                enemies.remove(i);
//            } else {
//                enemies.get(i).paint(g);
//            }
//        }
//
//        for (int i = 0; i < bullts.size(); i++) {
//            for (int j = 0; j < enemies.size(); j++) {
//                bullts.get(i).collideWithTank(enemies.get(j));
//            }
//
//            if (!bullts.get(i).isAlive()) {
//                bullts.remove(i);
//            } else {
//                bullts.get(i).paint(g);
//            }
//        }
//
//        for (int i = 0; i < explodes.size(); i++) {
//            if (!explodes.get(i).isAlive()) {
//                explodes.remove(i);
//            } else {
//                explodes.get(i).paint(g);
//            }
//        }
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void add(AbstraceGameObject bullt) {
        gameObjects.add(bullt);
    }


    public class TankListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
