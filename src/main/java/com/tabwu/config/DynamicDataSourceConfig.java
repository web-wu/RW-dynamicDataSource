package com.tabwu.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * @ProjectName: readWriteDynamicDb
 * @Author: tabwu
 * @Date: 2022/4/4 15:48
 * @Description: 将所有数据源都注入ioc容器
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDataSource() {
        return new DruidDataSource();
    }

    /**
     * 生成动态数据源
     * @param masterDataSource  主库
     * @param slaveDataSource   从库
     * @return
     */
    @Bean
    @Primary    //表示一个接口被多个子类实现时，使用@autowird根据类型注入时，优先注入此类
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        HashMap<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("master", masterDataSource);
        targetDataSources.put("slave", slaveDataSource);
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }
}
