package com.leekoko.pojo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ouezh
 * @since 2019-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Problem extends Model<Problem> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    /**
     * 题目
     */
    @TableField("TITLE")
    private String title;

    /**
     * 选项
     */
    @TableField("OPTION_VALUE")
    private String optionValue;

    /**
     * 答案
     */
    @TableField("ANSWER")
    private String answer;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
