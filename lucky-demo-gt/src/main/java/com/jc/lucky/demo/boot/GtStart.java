package com.jc.lucky.demo.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author magicnana
 * @date 2021/8/19 13:58
 */
@EnableFeignClients(basePackages = "com.jc.lucky.common.api")
@SpringBootApplication(scanBasePackages = "com.jc")
@MapperScan("com.jc.lucky.repository")
public class GtStart {

  private static final Logger logger = LoggerFactory.getLogger(GtStart.class);

  public static void main(String[] args) {
    SpringApplication.run(GtStart.class, args);
  }
}
