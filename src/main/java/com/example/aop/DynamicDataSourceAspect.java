package com.example.aop;

import com.example.annotation.DataSource;
import com.example.type.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DynamicDataSourceAspect {
    @Before("@annotation(dataSource)")//拦截我们的注解
    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
        String value = dataSource.value();
        System.out.println("选择数据源："+value);
        if (value.equals("master")){
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Master);
        }else if (value.equals("slave")){
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Slave);
        }else {
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Master);
        }

    }

    @After("@annotation(dataSource)") //清除数据源的配置
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        DataSourceType.clearDataBaseType();


    }
}
