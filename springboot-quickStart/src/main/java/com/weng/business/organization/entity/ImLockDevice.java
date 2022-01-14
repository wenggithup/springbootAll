package com.weng.business.organization.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 锁定设备表 <p>
 *
 * @author Wengchuanjie
 * @date 2022-01-14
 */
@Data
 @Accessors(chain = true)
@TableName("IM_LOCK_DEVICE")
public class ImLockDevice{

/** 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

/** 物理地址 */
    @TableField("mac_code")
    private String macCode;

/** 失效时间 */
    @TableField("lock_deadline")
    private Long lockDeadline;

/** 设备信息 */
    @TableField("device_info")
    private String deviceInfo;

/** 创建时间 */
    @TableField("create_time")
    private Long createTime;

/** 更新时间 */
    @TableField("update_time")
    private Long updateTime;
}
