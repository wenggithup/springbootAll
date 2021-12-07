package com.weng.demo.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *  <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-07
 */
@Data
@Accessors(chain = true)
@TableName("t_order_0")
public class TOrder0  {

    @TableId(value = "order_id", type = IdType.INPUT)
    private String orderId;

    @TableField("user_id")
    private Integer userId;

    @TableField("address_id")
    private Integer addressId;

    @TableField("status")
    private String status;
}
