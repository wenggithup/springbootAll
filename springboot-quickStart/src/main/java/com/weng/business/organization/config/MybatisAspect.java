package com.weng.business.organization.config;

import com.xiaoleilu.hutool.json.JSON;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @DATE: 2022/9/1 10:37 上午
 * @Author: ChuanJie.Weng
 * @Email: wengchuanjie@vrvmail.com.cn
 * @Description:
 */
@Component
@Aspect
public class MybatisAspect {

    private final static Logger logger = LoggerFactory.getLogger(MybatisAspect.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Around("execution(* com.weng.business.organization.mapper..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();
        String name1 = declaringClass.getName();
        String name = method.getName();
        System.out.println("开始调用 "+name1 +"#"+name);
        //这是执行的sql参数
        Object[] args = joinPoint.getArgs();

        MappedStatement statement = sqlSessionFactory.getConfiguration().getMappedStatement(method.getDeclaringClass().getName() + "." + method.getName());

        String newSql ="";
        BoundSql sql = statement.getBoundSql(args);
        //如果要修改sql中的参数
        if  (args.length >0) {
            BoundSql newBoundSsql = new BoundSql(sqlSessionFactory.getConfiguration(), newSql, sql.getParameterMappings(), sql.getParameterObject());
            MappedStatement mappedStatement = new MappedStatement.Builder(statement.getConfiguration(), statement.getId(), new MySqlSource(newBoundSsql), statement.getSqlCommandType()).build();
            args[0] = mappedStatement;
        }

        //通过反射直接修改sql语句，
        //原sql  select count(*) from user   修改后 select * from user limit 1,10
        String oldSql = sql.getSql();
        logger.info("old sql value is :{}",oldSql);


        SqlSource rowSqlSource = statement.getSqlSource();
        if (rowSqlSource instanceof StaticSqlSource){
            System.out.println("rowSqlSource instanceof StaticSqlSource yes");
        }
        Field sqlSource = rowSqlSource.getClass().getDeclaredField("sqlSource");
        sqlSource.setAccessible(true);
        Object staticsqlSourceObject = sqlSource.get(rowSqlSource);

        //业务逻辑部分
        if (!oldSql.contains("count")){
            return joinPoint.proceed();
        }
        newSql =  oldSql.replace("count(*)","*");
        newSql = newSql +"limit 1,10";
        Field field = staticsqlSourceObject.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(staticsqlSourceObject,newSql);
        System.out.println(sql.getSql());
//        Field field = sql.getClass().getDeclaredField("sql");
//        field.setAccessible(true);
//        field.set(sql,newSql);
//        System.out.println(sql.getSql());

        Object proceed = joinPoint.proceed();
        return proceed;
    }
}
