package com.jc.lucky.common.api;

import com.jc.lucky.common.entity.User;
import com.jc.lucky.common.vo.UserLoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "lucky-demo-user", url = "${api.lucky-demo-user}")
public interface UserApi {

  @RequestMapping(value = "/lucky/mid/user/sync", method = RequestMethod.POST)
  User syncUser(@RequestParam("phone")String phone);

}
