package com.jc.lucky.common.web;

/**
 * @author magicnana
 * @date 2021/9/1 21:02
 */
public class BaseConfig {

  protected final int MAX_THREADS = Runtime.getRuntime().availableProcessors() * 2;
  protected final int MIN_THREADS = Runtime.getRuntime().availableProcessors();
}
