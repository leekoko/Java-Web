package com.leekoko.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class Chapter {

    @TableId("ID")
    String id;

    @TableField("PARENT_CODE")
    String parentCode;

    @TableField("ID_CODE")
    String idCode;

    @TableField("NAME")
    String name;

    @TableField("OPEN")
    boolean open;

    @TableField("LEVEL")
    String level;

    @TableField("NODES")
    List<Chapter> nodes;


}
