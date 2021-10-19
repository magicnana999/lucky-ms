package com.jc.lucky.common.mysql;

import com.jc.lucky.common.web.BaseConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author magicnana
 * @date 2019/3/1 下午6:38
 */
@Configuration
public class DataSourceAutoConfig extends BaseConfig {

  private static final Logger logger = LoggerFactory.getLogger(DataSourceAutoConfig.class);

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    HikariDataSource ds = new HikariDataSource();
    ds.setMaximumPoolSize(MAX_THREADS);
    ds.setMinimumIdle(MIN_THREADS);

    if (logger.isInfoEnabled()) {
      logger.info("start -> DataSource");
    }
    return ds;
  }
}
