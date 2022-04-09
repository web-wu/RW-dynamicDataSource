package com.tabwu.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @ProjectName: readWriteDynamicDb
 * @Author: tabwu
 * @Date: 2022/4/4 15:29
 * @Description:  数据源动态切换
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object,Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);  //设置默认数据源
        super.setTargetDataSources(targetDataSources);  //设置数据源组
        super.afterPropertiesSet();
    }

    /**
     * 根据传入的数据库类型，在配置的数据源组的map中，判断使用的数据源类型
     * 若传入为null，则使用默认的数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return contextHolder.get();  //获取数据源类型
    }

    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);  //设置数据源类型
    }

    public static String getDataSource() {
        return contextHolder.get();   //获取数据源类型
    }

    public static void clearDataSource() {
        contextHolder.remove();  //移除数据源类型
    }
}
