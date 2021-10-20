package com.jc.lucky.common.entity;

import com.jc.lucky.common.base.AbstractEntity;
import lombok.Data;
import lombok.ToString;

/**
 * @author magicnana
 * @date 2021/10/20 15:22
 */
@Data
@ToString
public class User extends AbstractEntity {

  private String username;
  private String password;

}
