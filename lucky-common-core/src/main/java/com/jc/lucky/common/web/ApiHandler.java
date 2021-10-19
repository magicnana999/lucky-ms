package com.jc.lucky.common.web;

import com.jc.lucky.common.exception.HttpStatusException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * @author magicnana
 * @date 2021/9/4 17:02
 */
public class ApiHandler {

  public void handle(Api api, HttpServletRequest request) {
    handleScope(api,request);
  }

  private void handleScope(Api api, HttpServletRequest request) {
    if(api.scope().equals(Api.SCOPE_INTERNAL)){
      String header = request.getHeader(Api.INTERNAL_HEADER_KEY);
      if(StringUtils.isBlank(header) || !header.equals(Api.INTERNAL_HEADER_VAL)){
        throw new HttpStatusException(HttpStatus.UNAUTHORIZED);
      }
    }
  }
}
