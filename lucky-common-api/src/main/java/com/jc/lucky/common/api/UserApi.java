package com.jc.lucky.common.api;

import com.jc.lucky.common.vo.UserLoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient
public interface UserApi {

  @RequestMapping(value = "/lucky/mid/user/login", method = RequestMethod.POST)
  UserLoginVo login(String userName,String password);

}