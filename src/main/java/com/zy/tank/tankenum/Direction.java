package com.zy.tank.tankenum;

import java.util.Random;

public enum Direction {
    L, R, U, D;

    public static Direction randmDirection() {
        Random random = new Random();
        Direction[] values = Direction.values();
        return values[random.nextInt(values.length)];
    }
}
