package com.jc.lucky.common.redis;

import com.jc.lucky.common.web.BaseConfig;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author magicnana
 * @date 2021/8/23 15:00
 */
@Configuration
@ConditionalOnClass(RedisTemplate.class)
public class RedisAutoConfig extends BaseConfig {

  private static final Logger logger = LoggerFactory.getLogger(RedisAutoConfig.class);

  @Bean
  public LettuceClientConfigurationBuilderCustomizer lettuceClientConfigurationBuilderCustomizer(
      RedisProperties properties) {
    return builder -> {
      if (builder instanceof LettucePoolingClientConfigurationBuilder) {
        if (properties.getLettuce() != null) {
          if (properties.getLettuce().getPool() != null) {
            properties.getLettuce().getPool().setMaxActive(MAX_THREADS);
            properties.getLettuce().getPool().setMaxIdle(MIN_THREADS);
            properties.getLettuce().getPool().setMinIdle(MIN_THREADS);
          }
        }
        LettucePoolingClientConfigurationBuilder poolingBuilder =
            (LettucePoolingClientConfigurationBuilder) builder;
        poolingBuilder.poolConfig(getPoolConfig(properties.getLettuce().getPool()));

        if (logger.isInfoEnabled()) {
          logger.info("start -> LettuceClientConfigurationBuilderCustomizer");
        }
      }
    };
  }

  private GenericObjectPoolConfig<?> getPoolConfig(Pool properties) {
    GenericObjectPoolConfig<?> config = new GenericObjectPoolConfig<>();
    config.setMaxTotal(properties.getMaxActive());
    config.setMaxIdle(properties.getMaxIdle());
    config.setMinIdle(properties.getMinIdle());
    if (properties.getTimeBetweenEvictionRuns() != null) {
      config.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRuns().toMillis());
    }
    if (properties.getMaxWait() != null) {
      config.setMaxWaitMillis(properties.getMaxWait().toMillis());
    }
    return config;
  }

  @Bean
  @ConditionalOnMissingBean
  public RedisTemplate<String, Object> redisTemplate(
      RedisConnectionFactory redisConnectionFactory) {

    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(redisConnectionFactory);

    // 使用Jackson2JsonRedisSerialize 替换默认序列化(默认采用的是JDK序列化)
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    jackson2JsonRedisSerializer.setObjectMapper(om);

    // String的序列化
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    template.setKeySerializer(stringRedisSerializer);
    template.setHashKeySerializer(stringRedisSerializer);
    template.setValueSerializer(jackson2JsonRedisSerializer);
    template.setHashValueSerializer(jackson2JsonRedisSerializer);
    template.afterPropertiesSet();

    if (logger.isInfoEnabled()) {
      logger.info("start -> RedisTemplate");
    }
    return template;
  }

  @Bean
  @ConditionalOnMissingBean
  public RedisClient redisClient(RedisTemplate<String, Object> redisTemplate) {
    RedisClient rc = new RedisClient(redisTemplate);
    if (logger.isInfoEnabled()) {
      logger.info("start -> RedisClient");
    }
    return rc;
  }
}
