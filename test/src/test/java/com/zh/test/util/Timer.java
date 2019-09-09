package com.zh.test.util;

/**
 * 计时器
 */
public class Timer {
    /**
     * 统计一个方法的运行时间
     */
    public static <T> String time(TimerFunc<T> func) {
            long start = System.currentTimeMillis();
            func.func();
            long end = System.currentTimeMillis();
            double time = (end - start) / 1000.000;
            return time + "m";
    }
    @FunctionalInterface
    public interface TimerFunc<T> {
         void func(T... t) ;
    }
}
