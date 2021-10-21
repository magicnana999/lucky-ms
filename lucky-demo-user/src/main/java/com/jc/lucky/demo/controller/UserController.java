package com.jc.lucky.demo.controller;

import com.jc.lucky.common.base.AbstractController;
import com.jc.lucky.common.entity.User;
import com.jc.lucky.common.web.ApiResult;
import com.jc.lucky.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author magicnana
 * @date 2019/6/4 上午12:17
 */
@RestController
@RequestMapping(value = "/lucky/mid/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractController {

  @Resource public UserService userService;

  @Deprecated
  @ApiOperation("同步用户")
  @RequestMapping(value = "/sync", method = RequestMethod.POST)
  public ApiResult login(@RequestParam(value = "phone", required = false) String phone) {

    User user = userService.syncUser(phone);
    return new ApiResult(user);
  }
}
