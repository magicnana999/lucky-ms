package com.creolophus.lucky.common.web;

import com.creolophus.lucky.common.context.ApiContext;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ApiInterceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);
  @Resource
  private ApiHandler apiHandler;

  @Resource(name = "defaultApi")
  private Api defaultApi;

  protected void authenticate(HttpServletRequest request,HttpServletResponse response, Object handler) {
    if (handler instanceof HandlerMethod) {
      HandlerMethod hm = (HandlerMethod) handler;
      Api api = hm.getMethodAnnotation(Api.class);
      if (api == null) {
        api = defaultApi;
      }
      ApiContext.getContext().setApi(api);
      apiHandler.authenticate(api,request,response);
      apiHandler.preHandle(api, request,response);
    }
  }

  protected void completion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
    if (handler instanceof HandlerMethod) {
      HandlerMethod hm = (HandlerMethod) handler;
      Api api = hm.getMethodAnnotation(Api.class);
      if (api == null) {
        api = defaultApi;
      }
      ApiContext.getContext().setApi(api);
      apiHandler.postHandle(api, request,response,ex);
    }
    apiHandler.completion(request,response,handler,ex);
  }

  public String getPathPatterns() {
    return "/btb/**";
  }

  protected void preHandle(HttpServletRequest request) {
    MdcUtil.init(request.getRequestURI(), null);
  }

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    preHandle(request);
    authenticate(request, response,handler);
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    completion(request,response,handler,ex);
  }
}
