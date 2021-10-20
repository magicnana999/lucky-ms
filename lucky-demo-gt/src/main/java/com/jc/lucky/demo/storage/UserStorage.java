package com.jc.lucky.demo.storage;

import com.jc.lucky.common.base.AbstractStorage;
import com.jc.lucky.common.entity.User;
import com.jc.lucky.common.redis.RedisClient;
import com.jc.lucky.common.vo.UserLoginVo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author magicnana
 * @date 2021/10/20 19:11
 */
@Service
public class UserStorage extends AbstractStorage {

  private static String user_token_table = PREFIX + "user_token";
  private static String user_info_table = PREFIX + "user_info";

  @Resource public RedisClient redisClient;

  public void setToken(User user, UserLoginVo userLoginVo) {
    redisClient.hSet(user_token_table, userLoginVo.getToken(), user);
    redisClient.hSet(user_info_table, user.getId() + "", user);
  }
}
