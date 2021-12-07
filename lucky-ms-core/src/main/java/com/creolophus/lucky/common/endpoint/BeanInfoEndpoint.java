package com.creolophus.lucky.common.endpoint;

import com.zaxxer.hikari.HikariDataSource;
import javax.annotation.Resource;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * @author magicnana
 * @date 2021/9/4 14:34
 */
// 端点名称必须消息
@Endpoint(id = "beanprops")
public class BeanInfoEndpoint {

  @Resource
  private ApplicationContext applicationContext;


  @ReadOperation
  public String environmentEntry() {
    LettuceConnectionFactory ds = applicationContext.getBean(LettuceConnectionFactory.class);
    return "xx";

  }

}
