package com.aliware.tianchi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

public class TestCase {


    static Map map = new HashMap();
    static KeyValPair pair = new KeyValPair();

    public static void main(String[] args) {
        map.put(20880,1);
        KeyValPair.Entry entry = new KeyValPair.Entry();
        entry.port=20880;
        pair.put(entry);

        KeyValPair keyValPair = new KeyValPair();
        KeyValPair.Entry entry1 = new KeyValPair.Entry();
        entry1.port= 1;
        entry1.longAdder = new LongAdder();
        keyValPair.put(entry1);

        long begin = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            System.out.println(nextRTT());
        }


        System.out.println(System.currentTimeMillis() - begin );

        begin = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            pair.get(20880);
        }

        System.out.println(System.currentTimeMillis() - begin );
    }


    private static long nextRTT() {
        double u = ThreadLocalRandom.current().nextDouble();
        int x = 0;
        double cdf = 0;
        while (u >= cdf) {
            x++;
            cdf = 1 - Math.exp(-1.0D * 1 / 59 * x);
        }
        return x;
    }
}
