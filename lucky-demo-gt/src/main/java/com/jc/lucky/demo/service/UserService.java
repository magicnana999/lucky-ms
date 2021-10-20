package com.jc.lucky.demo.service;

import com.jc.lucky.common.api.UserApi;
import com.jc.lucky.common.base.AbstractService;
import com.jc.lucky.common.entity.User;
import com.jc.lucky.common.util.MD5Util;
import com.jc.lucky.common.vo.UserLoginVo;
import com.jc.lucky.demo.repository.UserMapper;
import com.jc.lucky.demo.storage.UserStorage;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author magicnana
 * @date 2020/11/25 10:02 AM
 */
@Service
public class UserService extends AbstractService {

  @Resource private UserMapper userMapper;

  @Resource public UserStorage userStorage;

  @Resource public UserApi userApi;

  public UserLoginVo register(String userName, String phone) {
    User user = createUser(userName, phone);
    UserLoginVo loginResult = login(user);
    cache(user, loginResult);
    return loginResult;
  }

  private void cache(User user,UserLoginVo userLoginVo){
    userStorage.setToken(user,userLoginVo);
  }

  private UserLoginVo login(User user) {
    String token =
        MD5Util.md5Hex(
            "" + user.getId() + System.currentTimeMillis() + Thread.currentThread().getId());
    return new UserLoginVo(token);
  }

  private User createUser(String username, String phone) {
    User user = userApi.syncUser(phone);
    user.setFirstName(username);
    userMapper.updateById(user);
    return user;
  }
}
