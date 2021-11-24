package com.weng.business.organization.mapper;

import com.weng.business.organization.entity.ImEnterpriseOrganization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 *  Mapper 接口 <p>
 *
 * @author wengchuanjie
 * @date 2021-11-21
 */
public interface ImEnterpriseOrganizationMapper extends BaseMapper<ImEnterpriseOrganization> {

    void insetByMap(Map<String, List<ImEnterpriseOrganization>> map);
}
