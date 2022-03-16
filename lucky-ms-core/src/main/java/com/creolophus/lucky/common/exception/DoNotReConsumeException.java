package com.creolophus.lucky.common.exception;

/**
 * @author magicnana
 * @date 2019/7/23 下午5:48
 */
public class DoNotReConsumeException extends CreolophusException {

  public DoNotReConsumeException(String message) {
    super(message);
  }

  public DoNotReConsumeException(String message, Throwable cause) {
    super(message, cause);
  }
}
