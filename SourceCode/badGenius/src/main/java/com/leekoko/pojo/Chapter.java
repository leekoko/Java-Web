package com.leekoko.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Chapter {

    String name;

    String id;

    boolean open;

    String level;

    List<Chapter> nodes;


}
