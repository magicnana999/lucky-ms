package com.creolophus.lucky.common.exception;

import com.creolophus.lucky.common.web.ApiResult;
import java.util.Map;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author magicnana
 * @date 2019/6/6 上午12:23
 */
public class AccessDeniedException extends HttpStatusException {

  private ApiResult apiResult;

  public AccessDeniedException(String message,ApiResult apiResult) {
    super(HttpStatus.UNAUTHORIZED, message);
    this.apiResult = apiResult;
  }

  public ApiResult getApiResult() {
    return apiResult;
  }
}
