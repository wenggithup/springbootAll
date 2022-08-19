package com.weng.business.organization.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 数据源配置信息表 <p>
 *
 * @author Wengcj
 * @date 2022-07-19
 */
@Data
 @Accessors(chain = true)
@TableName("IM_DBCONFIG_DATASOURCE_SUB")
public class ImDbconfigDatasourceSub{

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

/** 数据源ID */
    @TableField("dataSourceID")
    private String dataSourceID;

/** 驱动 */
    @TableField("driverClass")
    private String driverClass;

/** jdbcUrl */
    @TableField("jdbcUrl")
    private String jdbcUrl;

/** user */
    @TableField("user")
    private String user;

/** password */
    @TableField("password")
    private String password;

/** 读/写 */
    @TableField("writeOrRead")
    private Integer writeOrRead;
}
