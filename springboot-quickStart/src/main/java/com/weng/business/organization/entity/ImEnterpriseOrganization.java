package com.weng.business.organization.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 *  <p>
 *
 * @author wengchuanjie
 * @date 2021-11-21
 */
@Data
@Accessors(chain = true)
@TableName("IM_ENTERPRISE_ORGANIZATION")
@AllArgsConstructor
public class ImEnterpriseOrganization  {

    @TableField("orgId")
    private String orgId;

    @TableField("orgIds")
    private List<Long> orgIds;

    private String id;

}
