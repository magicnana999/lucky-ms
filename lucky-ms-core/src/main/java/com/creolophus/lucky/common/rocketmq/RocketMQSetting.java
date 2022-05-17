package com.creolophus.lucky.common.rocketmq;

import lombok.Data;

/**
 * @author magicnana
 * @date 2019/6/10 上午10:04
 */
@Data
public class RocketMQSetting {

  private String namesrvAddr = "47.99.173.26:11003";
  private String group = "default_group";

}
