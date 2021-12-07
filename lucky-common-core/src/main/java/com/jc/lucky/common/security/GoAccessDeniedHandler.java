package com.jc.lucky.common.security;

import com.jc.lucky.common.exception.HttpStatusException;
import com.jc.lucky.common.json.JSON;
import com.jc.lucky.common.web.ApiResult;
import com.jc.lucky.common.web.CorsUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author magicnana
 * @date 2019/5/27 下午6:21
 */
public class GoAccessDeniedHandler implements AccessDeniedHandler {

  private static final Logger logger = LoggerFactory.getLogger(GoAccessDeniedHandler.class);

  @Override
  public void handle(
      HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
      throws IOException {

    logger.error("权限不够" + request.getRequestURI());
    CorsUtil.cors(request, response);
    throw new HttpStatusException(HttpStatus.FORBIDDEN);

  }
}
