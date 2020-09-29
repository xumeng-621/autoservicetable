package com.ccb.hello.spring.boot.thymeleaf.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.ccb.hello.spring.boot.thymeleaf.dao2"},sqlSessionTemplateRef = "secondSqlSessionTemplate")
public class SecondDataSourceConfig {
    @Bean(name = "secondDataSource")
    @ConfigurationProperties("spring.second.datasource.druid") // prefix值必须是application.properteis中对应属性的前缀
    public DataSource secondDataSource() {

        return DataSourceBuilder.create().build();

    }
    @Bean
    public SqlSessionFactory secondSqlSessionFactory(
            @Qualifier("secondDataSource") DataSource dataSource) throws Exception {

        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
    @Bean(name = "secondDataSourceTransactionManger")
    public DataSourceTransactionManager masterTransactionManger(
            @Qualifier("secondDataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);

    }
    @Bean
    public SqlSessionTemplate secondSqlSessionTemplate(
            @Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {

        return new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory

    }
}
