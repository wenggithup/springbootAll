package com.weng.demo.business.mapper;

import com.weng.demo.business.entity.TOrder202101;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 *  Mapper 接口 <p>
 *
 * @author Wengchuanjie
 * @date 2021-12-09
 */
public interface TOrder202101Mapper extends BaseMapper<TOrder202101> {
    void
    insertTOrder(TOrder202101 tOrder202101);
}
