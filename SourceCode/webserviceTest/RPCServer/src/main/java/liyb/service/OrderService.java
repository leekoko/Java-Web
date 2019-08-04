package liyb.service;

import liyb.anno.Liyb;

@Liyb
public class OrderService {

    public void mod1(String name){
        System.out.println(name);
    }

    public String mod2(String name, Integer count){
        System.out.println(name + count);
        return name + "succ" + ":" + count;
    }

}
