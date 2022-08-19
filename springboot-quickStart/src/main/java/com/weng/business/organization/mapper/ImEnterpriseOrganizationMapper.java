package com.weng.business.organization.mapper;


import com.weng.business.organization.entity.ImEnterpriseOrganization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *  Mapper 接口 <p>
 *
 * @author wengchuanjie
 * @date 2021-11-21
 */
@Mapper
public interface ImEnterpriseOrganizationMapper {

    List<ImEnterpriseOrganization> selectList();

    Integer selectCount();
}
