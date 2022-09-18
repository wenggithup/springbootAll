package com.weng.demo.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-09
 */
@Data
@Accessors(chain = true)

public class TOrder202101 {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField("price")
    private Integer price;

    @TableField("name")
    private String name;

    @TableField("order_time")
    private Date orderTime;
}
