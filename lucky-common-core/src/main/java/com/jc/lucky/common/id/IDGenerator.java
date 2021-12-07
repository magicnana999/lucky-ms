package com.jc.lucky.common.id;

import org.springframework.stereotype.Service;

/**
 * @author magicnana
 * @date 2021/11/3 05:01
 */
@Service
public class IDGenerator {

  private static final SimpleSnowflakeID orderId = new SimpleSnowflakeID(1318936401676L);
  private static final SimpleSnowflakeID userId = new SimpleSnowflakeID(845996401676L);

  public Long orderId() {
    return orderId.nextId();
  }

  public Long userId() {
    return userId.nextId();
  }
}
