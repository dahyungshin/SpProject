package org.zerock.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"org.zerock.sample"})
@MapperScan(basePackages = {"org.zerock.mapper"})
public class RootConfig { 
	
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikaryConfig = new HikariConfig();
		//hikaryConfig.setDataSourceClassName("oracle.jdbc.driver.OracleDriver");
		//hikaryConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikaryConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikaryConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@localhost:1521:XE");
		hikaryConfig.setUsername("book_ex");
		hikaryConfig.setPassword("book_ex"); 
		 
		HikariDataSource dataSource = new HikariDataSource(hikaryConfig);
		return dataSource;
	} 
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqSessionFactory = new SqlSessionFactoryBean();
		sqSessionFactory.setDataSource(dataSource());
		return (SqlSessionFactory) sqSessionFactory.getObject();
	}
}
