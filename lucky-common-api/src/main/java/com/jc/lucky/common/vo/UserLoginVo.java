package com.jc.lucky.common.vo;

import com.jc.lucky.common.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author magicnana
 * @date 2021/10/20 15:24
 */
@Data
@ToString
@AllArgsConstructor
public class UserLoginVo extends User {

  private String token;

}
