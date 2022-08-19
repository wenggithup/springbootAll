package com.weng.business.organization.controller.datamove;

import com.weng.business.organization.dao.BaseDao;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @DATE: 2022/8/15 2:50 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Component
public class DataMoveUtil {
    @Autowired
    private BaseDao baseDao;
    private static final Integer SPILTED = 5;

    private static final Integer BATCH = 200;

    private static final String STASH ="_";
    /**
     * 升级脚本版本号
     */
    private final String version = "V6.0.1.6";
    public boolean insertTable() {
        //IM_USER_BASE/IM_USER_PWD/IM_USER_EXT->IM_USER_INFO 都是按USERID来分片的
        for (int i = 0; i < SPILTED; i++) {
            boolean flag = true;
            //200条记录一批次查询，组装数据
            int start = 0;
            Long userIdStart = 0L;
            while (flag){
                List<String> sql = new ArrayList<>(200);

                StringBuilder selectUserExt = new StringBuilder();
                selectUserExt.append("select * from IM_USER.IM_USER_BASE");
                selectUserExt.append(STASH).append(i).append(" where userID > ").append(userIdStart)
                        .append(" limit 0,200");


                List<UserBseEntity> userBaseList = baseDao.query(selectUserExt.toString(), new UserBseMapper());

                if (userBaseList.size()<BATCH){
                    flag = false;
                }
                if (userBaseList.size() ==0){
                    continue;
                }
                //IdList
                List<Long> userIdList = userBaseList.stream().map(UserBseEntity::getUserID).collect(Collectors.toList());
                Long maxUserId = userIdList.stream().max(Comparator.comparing(Long::intValue)).get();
                userIdStart = maxUserId;

                String userIdStr = idListStr(userIdList);


                String im_user_pwd = sqlStr("IM_USER_PWD", i, userIdStr);

                List<UserPwdEntity> userPwdEntityList = baseDao.query(im_user_pwd, new UserPwdMapper());

                Map<Long, List<UserPwdEntity>> userPwdEntityMap = userPwdEntityList.stream().collect(Collectors.groupingBy(UserPwdEntity::getUserID));


                List<UserExtEntity> userExtendEntityList = baseDao.query(sqlStr("IM_USER_EXT",i,userIdStr), new UserExtMapper());

                Map<Long, List<UserExtEntity>> userExtMap = userExtendEntityList.stream().collect(Collectors.groupingBy(UserExtEntity::getUserID));

                for (int k = 0; k < userBaseList.size(); k++) {
                    UserBseEntity entity = userBaseList.get(i);
                    UserPwdEntity userPwdEntity = new UserPwdEntity();
                    if (null != userPwdEntityMap &&  userPwdEntityMap.size()>0){
                        List<UserPwdEntity> list = userPwdEntityMap.get(entity.getUserID());
                        if (null != list && list.size()>0){
                            userPwdEntity = list.get(0);
                        }
                    }
                    UserExtEntity extendEntity = new UserExtEntity();
                    if (null != userExtMap &&  userExtMap.size()>0){
                        List<UserExtEntity> userExtEntities = userExtMap.get(entity.getUserID());
                        if (null != userExtEntities && userExtEntities.size()>0){
                            extendEntity = userExtEntities.get(0);
                        }
                    }

                    StringBuilder userInfoStr = new StringBuilder();
                    userInfoStr.append("INSERT INTO IM_USER.IM_USER_INFO").append(STASH).append(i)
                            .append("(`userID`, `SDKID`, `name`, `status`, `portraitURL`, `pwd`, `lockDeadline`, `pwdStrength`, `pwdUpdateTime`, `sex`, `area`" +
                                    ", `birthday`, `sign`, `timeZone`, `oriPortraitURL`, `extend`, `entExtend`, `regTime`, `orgID`, `roleID`, `bizStatus`" +
                                    ", `uploadFlag`, `oldStatus`, `realname`, `changeVersion`, `tagIds`, `securityLevel`, `externalId`) VALUES (");
                    userInfoStr
                            //base field
                            .append(assembleSql(entity.getUserID()))
                            .append(assembleSql(entity.getSKDID()))
                            .append(assembleSql(entity.getName()))
                            .append(assembleSql(entity.getStatus()))
                            .append(assembleSql(entity.getPortraitURL()))
                            //pwd field
                            .append(assembleSql(userPwdEntity.getPwd()))
                            .append(assembleSql(userPwdEntity.getLockDeadline()))
                            .append(assembleSql(userPwdEntity.getPwdStrength()))
                            .append(assembleSql(userPwdEntity.getPwdUpdateTime()))
                            //ext field
                            .append(assembleSql(Integer.valueOf(extendEntity.getColumn01())))
                            .append(assembleSql(extendEntity.getColumn02()))
                            .append(assembleSql(extendEntity.getColumn03()))
                            .append(assembleSql(extendEntity.getColumn06()))
                            .append(assembleSql(Integer.valueOf(extendEntity.getColumn07())))
                            .append(assembleSql(extendEntity.getColumn08()))
                            .append(assembleSql(extendEntity.getColumn09()))
                            .append(assembleSql(extendEntity.getColumn10()))
                            .append(assembleSql(Long.valueOf(extendEntity.getColumn11())))
                            .append(assembleSql(extendEntity.getColumn12()))
                            .append(assembleSql(Long.valueOf(extendEntity.getColumn13())))
                            .append(assembleSql(Integer.valueOf(extendEntity.getColumn14())))
                            .append(assembleSql(Integer.valueOf(extendEntity.getColumn15())))
                            .append(assembleSql(Integer.valueOf(extendEntity.getColumn16())))
                            .append(assembleSql(extendEntity.getColumn17()))
                            .append(assembleSql(Integer.valueOf(extendEntity.getColumn20())))
                            .append(assembleSql(extendEntity.getColumn21()))
                            .append(assembleSql(Integer.valueOf(extendEntity.getColumn22())))
                            .append(null == extendEntity.getColumn23()?"''":"'".concat(extendEntity.getColumn23()).concat("'"))
                            .append(")");
                    sql.add(userInfoStr.toString());
                }
                //组装数据
                baseDao.batchUpdateForList(sql);



                start = start+BATCH;
            }


        }


                return false;
    }

    private String assembleSql(Object o){
        String str = "";
        if (null != o) {
            if (o instanceof String) {
                str = "'".concat((String)o).concat("'").concat(",");
                return str;
            }else {
                return o+",";
            }
        }
        return "'',";

    }


    private String sqlStr(String tableName,int shard,String userIdStr){

        StringBuilder sqlStrBuilder = new StringBuilder();
        sqlStrBuilder.append("select * from IM_USER.")
                .append(tableName)
                .append(STASH)
                .append(shard)
                .append(" where userID in ")
                .append(userIdStr)
                .append(" limit 0,200");
        return sqlStrBuilder.toString();
    }

    /**
     * 拼接in 字符串
     * @param list
     * @return
     */
    private String idListStr(List<Long> list){
        StringBuilder idListStr = new StringBuilder();
        idListStr.append("(");
        for (int j = 0; j < list.size(); j++) {
            idListStr.append(list.get(j));
            if (j < list.size() -1){
                idListStr.append(",");
            }
        }
        idListStr.append(")");
        return idListStr.toString();
    }
}
