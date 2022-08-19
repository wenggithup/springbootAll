package com.weng.business.organization.controller.datamove;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @DATE: 2022/8/11 3:48 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class UserBseMapper implements RowMapper<UserBseEntity> {
    @Override
    public UserBseEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserBseEntity userBseEntity = new UserBseEntity();

        userBseEntity.setUserID(rs.getLong("userID"));
        userBseEntity.setUserType(rs.getByte("userType"));
        userBseEntity.setSKDID(rs.getLong("SDKID"));
        userBseEntity.setName(rs.getString("name"));
        userBseEntity.setStatus(rs.getInt("status"));
        userBseEntity.setPortraitURL(rs.getString("portraitURL"));
        return userBseEntity;
    }
}
