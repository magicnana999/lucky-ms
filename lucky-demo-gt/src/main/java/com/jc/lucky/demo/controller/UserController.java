package com.jc.lucky.demo.controller;

import com.jc.lucky.common.base.AbstractController;
import com.jc.lucky.common.web.ApiResult;
import com.jc.lucky.demo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping(value = "/lucky/front/gt/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractController {

  @Resource
  public UserService userService;

  @Deprecated
  @ApiOperation("注册")
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ApiResult login(
      @RequestParam(value = "username", required = false) String userName,
      @RequestParam(value = "phone", required = false) String phone) {

    userService.register(userName,phone);
    return new ApiResult(null);
  }

}
