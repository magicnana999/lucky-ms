package com.jc.lucky.common.base;

/**
 * @author magicnana
 * @date 2018/12/20 上午11:48
 */
public class AbstractStorage {

  public static final int SECOND = 1;
  public static final int MINUTE = 60;
  public static final int HOUR = MINUTE * 60;
  public static final int DAY = HOUR * 24;

  public static final String SE = ":";
  public static final String PREFIX = "lucky" + SE;

  public static final String OK = "OK";

  public static final int IDEMPOTENT = SECOND * 2;
}
