package com.ccb.hello.spring.boot.thymeleaf.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.ccb.hello.spring.boot.thymeleaf.dao"},sqlSessionTemplateRef = "firstSqlSessionTemplate")
public class FirstDataSourceConfig {
    @Bean(name = "firstDataSource")
    @Primary //必须加此注解
    @ConfigurationProperties("spring.first.datasource.druid") // prefix值必须是application.properteis中对应属性的前缀
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean
    public SqlSessionFactory firstSqlSessionFactory(
            @Qualifier("firstDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
    @Bean(name = "firstDataSourceTransactionManger")
    public DataSourceTransactionManager masterTransactionManger(
            @Qualifier("firstDataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);

    }
    @Bean
    public SqlSessionTemplate firstSqlSessionTemplate(
            @Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {

        return new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory

    }
}
