package com.jc.lucky.common.api;

import com.jc.lucky.common.entity.User;
import com.jc.lucky.common.vo.UserLoginVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "lucky-demo-user", url = "${feign.lucky-demo-user}")
public interface UserApi {

  @RequestMapping(value = "/lucky/mid/user/sync", method = RequestMethod.POST)
  User syncUser(String phone);

}
