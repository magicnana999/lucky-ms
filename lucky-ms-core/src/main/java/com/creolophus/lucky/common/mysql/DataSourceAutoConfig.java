package com.creolophus.lucky.common.mysql;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author magicnana
 * @date 2019/3/1 下午6:38
 */
@EnableTransactionManagement
@Configuration
public class DataSourceAutoConfig {

  private static final Logger logger = LoggerFactory.getLogger(DataSourceAutoConfig.class);

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    HikariDataSource ds = new HikariDataSource();
    if (logger.isInfoEnabled()) {
      logger.info("start -> DataSource");
    }
    return ds;
  }
}
