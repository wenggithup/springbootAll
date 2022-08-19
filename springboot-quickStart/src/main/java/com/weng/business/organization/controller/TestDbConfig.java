package com.weng.business.organization.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.JsonObject;
import com.weng.business.organization.entity.ImDbconfigApptable;
import com.weng.business.organization.entity.ImDbconfigDatasourceSub;
import com.weng.business.organization.entity.ImDbconfigTableds;
import com.weng.business.organization.mapper.ImDbconfigApptableMapper;
import com.weng.business.organization.mapper.ImDbconfigDatasourceSubMapper;
import com.weng.business.organization.mapper.ImDbconfigTabledsMapper;
import com.weng.business.organization.service.TestInerface;
import com.weng.business.organization.service.TestListener;
import com.weng.business.organization.service.impl.TestInerfaceImpl;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import io.grpc.internal.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @DATE: 2022/7/19 5:01 下午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@RestController
public class TestDbConfig {
    @Autowired
    ImDbconfigApptableMapper imDbconfigApptableMapper;

    @Autowired
    ImDbconfigDatasourceSubMapper imDbconfigDatasourceSubMapper;

    @Autowired
    ImDbconfigTabledsMapper imDbconfigTabledsMapper;

    @Autowired
    DataSource dataSource;
    @GetMapping("/testDbConfig")
    public void getDbConfig(){
        List<ImDbconfigApptable> imDbconfigApptables = imDbconfigApptableMapper.selectList(new QueryWrapper<>());
        Map<String, List<ImDbconfigApptable>> apptableMap = imDbconfigApptables.stream().collect(Collectors.groupingBy(ImDbconfigApptable::getAppServerID));
        //appServerId
        List<ImDbconfigDatasourceSub> imDbconfigDatasourceSubs = imDbconfigDatasourceSubMapper.selectList(new QueryWrapper<>());
        Map<String, List<ImDbconfigDatasourceSub>> dataSourceMap = imDbconfigDatasourceSubs.stream().collect(Collectors.groupingBy(ImDbconfigDatasourceSub::getDataSourceID));

        QueryWrapper<ImDbconfigTableds> imDbconfigTabledsQueryWrapper = new QueryWrapper<>();
        List<ImDbconfigTableds> imDbconfigTableds = imDbconfigTabledsMapper.selectList(imDbconfigTabledsQueryWrapper);
        Map<Long, List<ImDbconfigTableds>> tableMap = imDbconfigTableds.stream().collect(Collectors.groupingBy(ImDbconfigTableds::getAppTableID));
        Map<String,Object> map = new HashMap<>();

        for (Map.Entry<String, List<ImDbconfigApptable>> entry : apptableMap.entrySet()) {
            Set<String> set = new HashSet<>();

            String appTableID = entry.getKey();
            List<ImDbconfigApptable> value = entry.getValue();
            List<Object> objectList = new ArrayList<>();
            Map<String,Object> map1 = new HashMap<>();
            for (ImDbconfigApptable dbconfigApptable : value) {

                Map<String,Object> map2 = new HashMap<>();
                    map2.put("tableName", dbconfigApptable.getTableName());
                    map2.put("tableShardType", dbconfigApptable.getTableShardType());
                    List<ImDbconfigTableds> imDbconfigTableds1 = tableMap.get(dbconfigApptable.getAppTableID());
                    List tableList = new ArrayList();
                    if (CollectionUtil.isNotEmpty(imDbconfigTableds1)) {
                        for (ImDbconfigTableds imDbconfigTabled : imDbconfigTableds1) {
                            Map<String, Object> map3 = new HashMap<>();

                            if (null == imDbconfigTabled.getTableSegName()) {
                                map3.put("tableSegName", "");
                            } else {
                                map3.put("tableSegName", imDbconfigTabled.getTableSegName());
                            }
                            map3.put("datasourceId", imDbconfigTabled.getDataSourceID());
                            set.add(imDbconfigTabled.getDataSourceID());
                            tableList.add(map3);
                        }
                    }
                    map2.put("tableSeg", tableList);
                    objectList.add(map2);
                }


                map1.put("tableInfo", objectList);

                List<Object> list = new ArrayList<>();
                for (String s : set) {
                    Map<String, Object> map5 = new HashMap<>();
                    ImDbconfigDatasourceSub datasourceSub = imDbconfigDatasourceSubMapper.selectOne(new QueryWrapper<ImDbconfigDatasourceSub>().lambda()
                            .eq(ImDbconfigDatasourceSub::getDataSourceID, s));
                    map5.put("datasourceId", s);
                    String[] split = datasourceSub.getJdbcUrl().split("\\?");

                    String str = split[0];

                    String mysql = str.substring(5, 10);

                    map5.put("databaseId", mysql);

                    String[] split1 = str.split("//");

                    String string = split1[1];

                    String[] ip = string.split(":");
                    map5.put("ip", ip[0]);

                    String s1 = ip[1];
                    String[] split2 = s1.split("/");
                    String port = split2[0];
                    map5.put("port", port);
                    String datasourceName = split2[1];
                    map5.put("databaseName", datasourceName);
                    map5.put("username", datasourceSub.getUser());
                    map5.put("password", datasourceSub.getPassword());
                    map5.put("codecEnabled", false);
                    map5.put("key", "jgz81");

                    list.add(map5);
                }
                map1.put("datasourceInfo", list);

                map.put(appTableID, map1);

        }
        String s = JSONUtil.toJsonStr(map);
        System.out.println(s);
    }


    @GetMapping("/testDs")
    public void testDs() throws SQLException {
        Connection connection = dataSource.getConnection();
        // 返回结果
        List<Map<String, String>> resultList = new ArrayList<>();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(
                    "SELECT a.tableName AS \"tableName\",b.tableSegName AS \"tableSegName\",b.dataSourceID AS \"dataSourceID\" FROM IM_DBCONFIG_APPTABLE a,IM_DBCONFIG_TABLEDS b WHERE a.appTableID = b.appTableID AND a.appServerID =? AND a.appServerVersion =?");
            prepareStatement.setString(1, "userBaseServer");
            prepareStatement.setString(2, "1.0");
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, String> result = new HashMap<>(10);
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String key = metaData.getColumnLabel(i);
                    if (StringUtils.isEmpty(key)) {
                        key = metaData.getCatalogName(i);
                    }
                    result.put(key, resultSet.getString(i));
                }
                resultList.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(resultList.toString());
        Map<String, String> tableMap = new HashMap<String, String>(10);
        Set<String> set = new HashSet<String>();
        for (Map<String, String> map : resultList) {

            if (map.get("tableSegName") != null && !map.get("tableSegName").equals("")) {
                tableMap.put(map.get("tableSegName"), map.get("dataSourceID"));
            } else {
                tableMap.put(map.get("tableName"), map.get("dataSourceID"));
            }
            set.add(map.get("dataSourceID"));
        }

        System.out.println(JSONUtil.toJsonStr(tableMap));
    }


}
