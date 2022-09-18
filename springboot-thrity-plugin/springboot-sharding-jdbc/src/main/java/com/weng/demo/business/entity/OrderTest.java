package com.weng.demo.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-09
 */
@Data
@Accessors(chain = true)
@TableName("order_test")
public class OrderTest {

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @TableField("remark")
    private Integer remark;
}
