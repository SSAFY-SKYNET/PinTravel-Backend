package com.ssafy.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration // 스프링 설정 클래스를 나타내는 어노테이션
@MapperScan(basePackages = "com.ssafy.repository.**") // MyBatis 매퍼 인터페이스를 스캔할 패키지 경로 설정
public class MybatisConfig {
    @Bean // 스프링 빈으로 등록하기 위한 어노테이션
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource); // 데이터 소스 설정

        // MyBatis Configuration 추가
        org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
        mybatisConfig.setMapUnderscoreToCamelCase(true); // snake_case를 camelCase로 매핑 활성화
        sqlSessionFactoryBean.setConfiguration(mybatisConfig); // MyBatis 설정 추가

        return sqlSessionFactoryBean.getObject(); // SqlSessionFactory 객체 반환
    }

    @Bean // 스프링 빈으로 등록하기 위한 어노테이션
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory); // SqlSessionTemplate 객체 생성 및 반환
    }

    @Bean // 스프링 빈으로 등록하기 위한 어노테이션
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource); // 트랜잭션 매니저 객체 생성 및 반환
    }
}
