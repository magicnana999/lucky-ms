package com.jc.lucky.demo.controller;

import com.jc.lucky.common.base.AbstractController;
import com.jc.lucky.common.web.ApiResult;
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
@RequestMapping(value = "/lucky/mid/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractController {

  @Resource
  private UserService userService;



  @Deprecated
  @ApiOperation("登录-用户名+密码")
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ApiResult login(
      @RequestParam(value = "portrait",required = false) String portrait,
      @RequestParam(value = "userName",required = false) String userName,
      @RequestParam(value = "levelId", required = false) Long levelId,
      @RequestParam(value = "subjectId", required = false) Long subjectId) {

    UserBaseRet user = userService.initPassword(currentUserId(), userName, levelId, subjectId, portrait);
    return new ApiResult(user);
  }

}
