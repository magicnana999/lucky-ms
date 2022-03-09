package com.creolophus.lucky.common.beetlsql;

import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.starter.SQLManagerCustomize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author magicnana
 * @date 2022/3/9 20:46
 */
@Configuration
@ConditionalOnClass(SQLManagerCustomize.class)
public class BeetlSqlConfig {

  private static final Logger logger = LoggerFactory.getLogger(BeetlSqlConfig.class);

  @Bean
  public SQLManagerCustomize mySQLManagerCustomize() {
    return (sqlMangerName, manager) -> {
      manager.setInters(new Interceptor[] {new LineSqlPrintInterceptor()});
      logger.info("start setInterceptor of LineSqlPrintInterceptor");
    };
  }
}
