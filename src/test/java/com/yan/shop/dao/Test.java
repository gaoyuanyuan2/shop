package com.yan.shop.dao;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random r = new Random(1);
        for (int i = 0; i <100 ; i++) {
            System.out.println(r.nextInt(1000));
        }

    }
}
