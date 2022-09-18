package com.weng.demo.business.mapper;

//import com.baomidou.dynamic.datasource.annotation.DS;

import com.weng.demo.business.entity.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * Mapper 接口 <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-08
 */
//@DS("gits_sharding")
public interface TOrderMapper extends BaseMapper<TOrder> {

    void insertOrder(TOrder tOrder);
}
