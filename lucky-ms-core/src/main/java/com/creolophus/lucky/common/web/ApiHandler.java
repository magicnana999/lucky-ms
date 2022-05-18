package com.creolophus.lucky.common.web;

import com.creolophus.lucky.common.exception.HttpStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * @author magicnana
 * @date 2021/9/4 17:02
 */
public class ApiHandler {

  private static final Logger logger = LoggerFactory.getLogger(ApiHandler.class);

  @Resource
  private List<ApiSecurity> apiSecurityList = new ArrayList();

  public void preHandle(Api api, HttpServletRequest request) {
    handleScope(api, request);
    HandlePreSecurity(api, request);
  }

  protected void HandlePreSecurity(Api api, HttpServletRequest request) {
    if (StringUtils.isNoneBlank(api.encrypt())) {
      for (ApiSecurity apiSecurity : apiSecurityList) {
        if (apiSecurity.encrypt().equals(api.encrypt())) {
          apiSecurity.encrypt(request);
          return;
        }
      }
    }
  }

  protected void HandlePostSecurity(Api api, HttpServletResponse response) {
    if (StringUtils.isNoneBlank(api.decrypt())) {
      for (ApiSecurity apiSecurity : apiSecurityList) {
        if (apiSecurity.decrypt().equals(api.decrypt())) {
          apiSecurity.decrypt(response);
          return;
        }
      }
    }
  }

  protected void handleScope(Api api, HttpServletRequest request) {
    if (api.scope().equals(Api.SCOPE_INTERNAL)) {
      String header = request.getHeader(Api.INTERNAL_HEADER_KEY);

      if (logger.isDebugEnabled()) {
        logger.debug("{} {} {}", Api.SCOPE_INTERNAL, Api.INTERNAL_HEADER_KEY, header);
      }

      if (StringUtils.isBlank(header) || !header.equals(Api.INTERNAL_HEADER_VAL)) {
        throw new HttpStatusException(HttpStatus.UNAUTHORIZED);
      }
    }
  }

  public void postHandle(Api api, HttpServletRequest request, HttpServletResponse response, Exception ex) {
    if(ex!=null){
      HandlePostSecurity(api,response);
    }
  }
}
