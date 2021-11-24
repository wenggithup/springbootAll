package com.ycloud.cloudlink.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类(无逻辑删除)
 *
 * @author XiangYu.Geng
 * @date 2021-03-10
 */
@Data
public class BaseEntityNotLogic implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键Id */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /** 创建时间(插入操作自动填充) */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /** 更新时间(更新操作自动填充) */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;
}
