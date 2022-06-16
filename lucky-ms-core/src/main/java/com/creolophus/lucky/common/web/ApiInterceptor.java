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

  @Resource
  private Api defaultApi;

  public String getPathPatterns() {
    return "/**";
  }


  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {

    MdcUtil.init(request.getRequestURI(), null);
    if (apiHandler.ignore(request)) {
      return true;
    }

    if (handler instanceof HandlerMethod) {
      HandlerMethod hm = (HandlerMethod) handler;
      Api api = hm.getMethodAnnotation(Api.class);
      if (api == null) {
        api = defaultApi;
      }
      ApiContext.getContext().setApi(api);
      apiHandler.preHandle(api, request, response);
      apiHandler.handleScope(api, request);
      apiHandler.authenticate(api, request, response);
      apiHandler.HandlePreSecurity(api, request, response);
    }

    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    if (handler instanceof HandlerMethod) {
      HandlerMethod hm = (HandlerMethod) handler;
      Api api = hm.getMethodAnnotation(Api.class);
      if (api == null) {
        api = defaultApi;
      }
      ApiContext.getContext().setApi(api);

      if (ex != null) {
        apiHandler.HandlePostSecurity(api, request, response);
      }
      apiHandler.completion(request, response, handler, ex);
    }
  }
}
