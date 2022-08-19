package com.weng.business.organization.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @DATE: 2022/8/15 2:46 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Component
public class BaseDao<T> {

    private final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    @Resource
    JdbcTemplate jdbcTemplate;
    public void batchUpdate(String[] sql) {
        logger.error(sql.toString());
        jdbcTemplate.batchUpdate(sql);
    }

    public void batchUpdateForList(List<String> sql) {
        if (sql.size() > 0) {
            String[] sqlArray = sql.toArray(new String[sql.size()]);
            jdbcTemplate.batchUpdate(sqlArray);
        }
    }

    public List<T> query(String sql, RowMapper<T> rowMapper) {
        logger.debug("#query sql:{}", sql);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<T> query(String sql, Object[] args, RowMapper<T> rowMapper) {
        logger.debug("#query sql:{}, params:{}", sql, args);
        return jdbcTemplate.query(sql, args, rowMapper);
    }
}
