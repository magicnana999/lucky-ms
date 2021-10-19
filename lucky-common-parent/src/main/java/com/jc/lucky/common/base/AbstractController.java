package com.jc.lucky.common.base;


import com.jc.lucky.common.context.ApiContext;

/**
 * @author magicnana
 * @date 2019/5/20 下午2:31
 */
public class AbstractController {

  protected Long userId(){
    return ApiContext.getContext().getUserId();
  }
}
