package com.weng.business.organization.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.RandomUtils;

/**
 * 表数据源 <p>
 *
 * @author Wengcj
 * @date 2022-07-19
 */
@Data
 @Accessors(chain = true)
@TableName("IM_DBCONFIG_TABLEDS")
public class ImDbconfigTableds{

    @TableId(value = "tableDSID", type = IdType.AUTO)
    private Long tableDSID;

/** 应用表ID */
    @TableField("appTableID")
    private Long appTableID;

/** 分表名 */
    @TableField("tableSegName")
    private String tableSegName;

/** 数据源ID */
    @TableField("dataSourceID")
    private String dataSourceID;

}
