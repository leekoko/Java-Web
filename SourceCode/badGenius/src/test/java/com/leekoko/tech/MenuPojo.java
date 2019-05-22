package com.leekoko.tech;

import lombok.Data;

import java.util.List;

@Data
public class MenuPojo {
    String title;
    String id;
    String img;
    boolean hidden;
    List<MenuDetailPojo> url;

}
