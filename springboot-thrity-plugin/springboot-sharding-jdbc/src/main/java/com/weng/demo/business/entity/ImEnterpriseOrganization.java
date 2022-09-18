package com.weng.demo.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-08
 */
@Data

@Accessors(chain = true)
@TableName("IM_ENTERPRISE_ORGANIZATION")
public class ImEnterpriseOrganization {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("orgId")
    private String orgId;

    @TableField("orgIds")
    private String orgIds;
}
