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

  public void authenticate(HttpServletRequest request, HttpServletResponse response);

  public void encrypt(HttpServletRequest request, HttpServletResponse response);

  public void decrypt(HttpServletRequest request, HttpServletResponse response);

}
