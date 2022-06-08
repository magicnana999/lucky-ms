package com.creolophus.lucky.common.web;

import com.creolophus.lucky.common.error.ApiError;
import com.creolophus.lucky.common.json.GsonUtil;
import java.util.Collections;
import java.util.function.Supplier;

/**
 * @author magicnana
 * @date 2019/5/15 下午2:16
 */
public abstract class ApiResult<T> implements java.io.Serializable {

  public abstract int getCode();

  public abstract T getData();

  public abstract String getMessage();


  public String toString() {
    return GsonUtil.toJson(this);
  }

}
