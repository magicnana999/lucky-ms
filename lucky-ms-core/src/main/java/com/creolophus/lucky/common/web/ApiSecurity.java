package com.creolophus.lucky.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author magicnana
 * @date 2022/5/18 21:58
 */
public interface ApiSecurity {

  String auth();

  String encrypt();

  String decrypt();

  public void authenticate(HttpServletRequest request);

  public void encrypt(HttpServletRequest request);

  public void decrypt(HttpServletResponse response);

}
