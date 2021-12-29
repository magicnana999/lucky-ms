package com.creolophus.lucky.common.exception;

import com.creolophus.lucky.common.error.ApiError;


/**
 * @author magicnana
 * @date 2019/6/6 上午12:23
 */
public class NoContentException extends ApiException {

  private ApiError apiErrorCode;

  public NoContentException(ApiError apiErrorCode) {
    super(apiErrorCode.getMessage());
    this.apiErrorCode = apiErrorCode;
  }

  public ApiError getApiError() {
    return apiErrorCode;
  }
}
