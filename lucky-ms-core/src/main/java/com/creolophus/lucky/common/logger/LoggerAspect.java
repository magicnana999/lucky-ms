package com.creolophus.lucky.common.logger;

import com.alibaba.fastjson.JSON;
import com.creolophus.lucky.common.json.FastJsonDesensitizeFilter;
import com.creolophus.lucky.common.web.MdcUtil;
import com.creolophus.lucky.common.context.ApiContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * 朝辞白帝彩云间 千行代码一日还 两岸领导啼不住 地铁已到回龙观
 *
 * @author magicnana
 * @date 2019/9/18 下午2:05
 */
@Aspect
public class LoggerAspect {

  private static final ConcurrentHashMap<Class, Logger> loggerTable = new ConcurrentHashMap<>();
  private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

  private static FastJsonDesensitizeFilter filter = new FastJsonDesensitizeFilter();

  @AfterReturning(pointcut = "inService()", returning = "result")
  public void doAfter(JoinPoint joinPoint, Object result) {
    mdcMethod(joinPoint);
    printAfter(joinPoint, result);
  }

  @Before("inService()")
  public void doBefore(JoinPoint joinPoint) {
    mdcMethod(joinPoint);
    printBefore(joinPoint);
  }

  @AfterThrowing(pointcut = "inService()", throwing = "a")
  public void doError(JoinPoint joinPoint, Throwable a) {
    mdcMethod(joinPoint);
  }

  private Logger getLogger(JoinPoint pjp) {
    Logger logger = loggerTable.get(pjp.getSignature().getDeclaringType());
    if (logger != null) {
      return logger;
    } else {
      logger = LoggerFactory.getLogger(pjp.getSignature().getDeclaringType());
      loggerTable.putIfAbsent(pjp.getSignature().getDeclaringType(), logger);
      return logger;
    }
  }

  private String getMethodName(JoinPoint joinPoint) {
    return joinPoint.getSignature().getName();
  }

  @Pointcut(
      "execution(* com..*.dao..*.*(..))"
          + "|| execution(* com..*.service..*(..))"
          + "|| execution(* com..*.controller..*(..))"
          + "|| execution(* com..*.storage..*(..))"
          + "|| execution(* com..*.scheduler..*(..))"
          + "|| execution(* com..*.component..*(..))"
          + "|| execution(* com..*.server..*(..))"
          + "|| execution(* com..*.job..*(..))"
          + "|| execution(* com..*.mapper..*(..))"
          + "|| execution(* com..*.repository..*(..))"
          + "|| execution(* com..*.cache..*(..))"
          + "|| execution(* com..*.assemble..*(..))"
          + "|| execution(* com..*.consumer..*(..))"
          + "|| execution(* com..*.producer..*(..))"
          + "|| execution(* com..*.mq..*(..))"
          + "|| execution(* com..*.iot..*(..))"
          + "|| execution(* com..*.feign..*(..))")
  public void inService() {}

  private boolean isCanBeToString(Object obj) {
    if (obj == null) {
      return false;
    } else {
      return BeanUtils.isSimpleValueType(obj.getClass()) || obj instanceof java.io.Serializable;
    }
  }

  private void mdcMethod(JoinPoint joinPoint) {
    MdcUtil.setMethod(getMethodName(joinPoint));
  }

  private String parameterToString(JoinPoint joinPoint) {
    Object[] objs = joinPoint.getArgs();
    List<Object> list = new ArrayList(objs == null ? 0 : objs.length);
    for (int i = 0; i < objs.length; i++) {
      Object obj = objs[i];

      if (isCanBeToString(obj)) {
        list.add(obj);
      }
    }

    try {
      return JSON.toJSONString(list,filter);
    } catch (Throwable e) {
      return "could not parse to string";
    }
  }

  private void printAfter(JoinPoint joinPoint, Object result) {
    if (logger.isDebugEnabled()) {
      String rt = resultToString(result);
      ApiContext.getContext().setApiResult(rt);
      getLogger(joinPoint).debug(rt);
    }
  }

  private void printBefore(JoinPoint joinPoint) {
    if (logger.isDebugEnabled()) {
      String parameters = parameterToString(joinPoint);
      getLogger(joinPoint).debug(parameters);
    }
  }

  private String resultToString(Object result) {
    String temp = "";
    if (result != null) {
      temp = toJSONString(result);
    }
    return StringUtils.isBlank(temp) ? "{}" : temp;
  }

  private String toJSONString(Object object) {
    if (isCanBeToString(object)) {
      return JSON.toJSONString(object,filter);
    } else {
      return object.toString();
    }
  }
}
