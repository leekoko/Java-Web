package com.aliware.tianchi;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * key-val
 *
 * @author 孙证杰
 * @email 200765821@qq.com
 * @date 2019/7/17 16:44
 */
public class KeyValPair {

    Entry[] table = new Entry[3];
    int size = 0;

    public int size() {
        return size;
    }

    public Entry get(int port) {
        if(size == 0){
            return null;
        }

        for (Entry entry : table) {
            if(entry != null && entry.port == port){
                return entry;
            }
        }

        return null;
    }

    public Entry put(Entry entry) {
        table[size++] = entry;
        return null;
    }

    public static class Entry{
        int port;
        LongAdder longAdder;
        LongAdder trc;
        AtomicLong tt;

        public int rrt(){
            return (int) (tt.get()/(trc.intValue()+1));
        }
    }
}
