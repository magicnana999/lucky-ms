package com.jc.lucky.demo.service;

import com.jc.lucky.common.api.UserApi;
import com.jc.lucky.common.base.AbstractService;
import com.jc.lucky.common.entity.User;
import com.jc.lucky.demo.repository.UserMapper;
import com.jc.lucky.demo.storage.UserStorage;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author magicnana
 * @date 2020/11/25 10:02 AM
 */
@Service
public class UserService extends AbstractService implements UserApi {

    @Resource
    private UserMapper userMapper;

    @Resource
    public UserStorage userStorage;



    @Override
    public void syncUser(String phone) {
        User user = new User();
        user.setMobile(phone);
        userMapper.insert(user);
    }
}
