package com.leekoko.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class HGPageProcesserTest {

    @Test
    public void cutStr(){
        String str = "https://wl.scutde.net/edu3/edu3/learning/interactive/activecourseexam/list.html?syllabusId=402881382d2fb65b012d30d8df9c0117&pageNum=2&pageSize=10&scopeType=all&term=2&isPublished=Y&isAutoSave=N";
        String str2 = "https://wl.scutde.net/edu3/edu3/learning/interactive/activecourseexam/list.html";
        int cutEnd = str2.indexOf("&pageNum=");
        String result = str;
        if(cutEnd > 0){
            result = str.substring(0, cutEnd);
        }
        System.out.println(result);
    }

}