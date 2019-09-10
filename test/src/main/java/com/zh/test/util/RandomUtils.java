package com.zh.test.util;

import java.util.Random;

public class RandomUtils {

    private final static Random r = new Random();
    private final static String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final static char[] c = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '0','1','2','3','4','5','6','7','8','9'
    };
    private static final int ch= 0x3f;
    public final static int h = Integer.MAX_VALUE;
    private static final int cc= ch & h;
    public static void r(){

    }

    public static void main(String[] args) {

        System.out.println(getString(1));
        System.out.println(getInt(1));

    }

    public static String getString(int len){
        StringBuilder str = new StringBuilder(Math.max(0,len));
        for (int i = 0; i < Math.max(0,len); i++) {
            str.append(RandomUtils.c[Math.min(r.nextInt() & cc, c.length-1)]);
        }
        return str.toString();
    }



    public static int getInt(int len){
        int i = 15;
        for (int j = 0; j < len-1; j++) {
            i <<= 1 | 1;
        }


        System.out.println(i);
        for (int j = 0; j < 10; j++) {
            System.out.println(r.nextInt() & i);
        }
        return r.nextInt() >> i;
    }


}
