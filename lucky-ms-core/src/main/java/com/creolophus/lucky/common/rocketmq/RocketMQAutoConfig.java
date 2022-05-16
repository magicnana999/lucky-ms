package com.creolophus.lucky.common.rocketmq;

//import org.apache.rocketmq.client.impl.producer.DefaultMQProducerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DefaultMQProducerImpl.class)
public class RocketMQAutoConfig {

  private static final Logger logger = LoggerFactory.getLogger(RocketMQAutoConfig.class);


  @Value("${spring.rocketmq.addr}")
  private String namesrvAddr;

  @Value("${spring.rocketmq.group:default}")
  private String group;


  @Bean
  @ConditionalOnMissingBean
  public RocketMQProducer rocketMQProducer() {
    return new RocketMQProducer(namesrvAddr, group);
  }
}
