package com.creolophus.lucky.common.exception;

/**
 * @author magicnana
 * @date 2019/6/24 下午5:51
 */
public class DirectlyMessageException extends AutoStatusException {

  public DirectlyMessageException(String message) {
    super(message);
  }

  public DirectlyMessageException(String message, int httpStatusCode) {
    super(message, httpStatusCode);
  }
}
