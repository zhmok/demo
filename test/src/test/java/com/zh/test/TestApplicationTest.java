package com.zh.test;

import com.zh.test.util.Timer;
import org.junit.Test;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//@Slf4j
public class TestApplicationTest {



    @Test
    public void test() throws IOException {
        new Thread(()->{
            System.out.println("arr: "+Timer.time(this::arrayInsertTest));
        }).start();
        new Thread(()->{

            System.out.println("linked: "+Timer.time(this::linkedInsertTest));
        }).start();

        System.in.read();
//        log.error("asd");
    }

    int size=10 *   1000000*3;
    public void arrayInsertTest(Object[] objects){
        List<Object> arr = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            WeakReference<String> stringSoftReference = new WeakReference<String>(i+"i");
            arr.add(stringSoftReference);
        }
    }
    public void linkedInsertTest(Object[] objects){
        List<Object> arr = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            WeakReference<String> stringSoftReference = new WeakReference<String>(i+"i");
            arr.add(stringSoftReference);
        }
    }


}