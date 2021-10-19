package com.jc.lucky.common.exception;

import com.jc.lucky.common.error.ApiError;

/**
 * @author magicnana
 * @date 2019/6/24 下午5:51
 */
public class ErrorCodeException extends ApiException {

  private ApiError apiErrorCode;

  public ErrorCodeException(ApiError apiErrorCode) {
    super(apiErrorCode.getMessage());
    this.apiErrorCode = apiErrorCode;
  }

  public ApiError getApiError() {
    return apiErrorCode;
  }
}
