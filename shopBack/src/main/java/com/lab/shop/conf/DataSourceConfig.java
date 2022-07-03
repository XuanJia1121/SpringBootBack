package com.lab.shop.conf;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
	
	//DataSource設定
	@Bean
	public DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf-8");
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("xuan");
		return dataSourceBuilder.build();
	}

}
