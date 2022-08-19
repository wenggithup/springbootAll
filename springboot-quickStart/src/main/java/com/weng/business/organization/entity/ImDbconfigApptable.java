package com.weng.business.organization.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 应用表信息 <p>
 *
 * @author Wengcj
 * @date 2022-07-19
 */
@Data
 @Accessors(chain = true)
@TableName("IM_DBCONFIG_APPTABLE")
public class ImDbconfigApptable{

    @TableId(value = "appTableID", type = IdType.AUTO)
    private Long appTableID;

/** 应用服务ID */
    @TableField("appServerID")
    private String appServerID;

/** 应用服务版本 */
    @TableField("appServerVersion")
    private String appServerVersion;

/** 表名 */
    @TableField("tableName")
    private String tableName;

/** 分表类型，如％5 */
    @TableField("tableShardType")
    private String tableShardType;
}
