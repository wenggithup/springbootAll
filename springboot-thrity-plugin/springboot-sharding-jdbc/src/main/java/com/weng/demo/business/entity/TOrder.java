package com.weng.demo.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-08
 */
@Data
@Accessors(chain = true)
@TableName("t_order")
public class TOrder {

    @TableId(value = "order_id", type = IdType.INPUT)
    private Long orderId;

    @TableField("user_id")
    private Integer userId;

    @TableField("address_id")
    private Integer addressId;

    @TableField("status")
    private String status;
}
