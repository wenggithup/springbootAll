package com.weng.business.organization.controller.datamove;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @DATE: 2022/8/11 5:50 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
public class UserPwdMapper implements RowMapper<UserPwdEntity> {
    @Override
    public UserPwdEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserPwdEntity userPwdEntity = new UserPwdEntity();
        userPwdEntity.setUserID(rs.getLong("userID"));
        userPwdEntity.setSKDID(rs.getLong("SDKID"));
        userPwdEntity.setPwd(rs.getString("pwd"));
        userPwdEntity.setLockDeadline(rs.getLong("lockDeadline"));
        userPwdEntity.setPwdStrength(rs.getInt("pwdStrength"));
        userPwdEntity.setPwdUpdateTime(rs.getLong("pwdUpdateTime"));
        return userPwdEntity;
    }
}
