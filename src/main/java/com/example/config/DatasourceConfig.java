package com.example.config;

import com.example.datasource.DynamicDatasource;
import com.example.type.DataSourceType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatasourceConfig {
    @Primary
    @Bean("masterDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slaveDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DynamicDatasource dynamicDatasource(@Qualifier("masterDatasource") DataSource masterDatasource, @Qualifier("slaveDatasource") DataSource slaveDatasource){
        Map<Object,Object> targetDatasource=new HashMap<Object,Object>(){{
            put(DataSourceType.DataBaseType.Master,masterDatasource);
            put(DataSourceType.DataBaseType.Slave,slaveDatasource);
        }};
        DynamicDatasource dynamicDatasource = new DynamicDatasource();
        dynamicDatasource.setTargetDataSources(targetDatasource);
        dynamicDatasource.setDefaultTargetDataSource(masterDatasource);
        return dynamicDatasource;
    }
}
