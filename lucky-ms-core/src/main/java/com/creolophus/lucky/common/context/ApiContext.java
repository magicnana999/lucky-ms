package com.creolophus.lucky.common.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author magicnana
 * @date 2019/7/3 上午10:36
 */
@Data
public class ApiContext<T> implements Serializable {

  private String userId;
  private String token;
  private T api;
  private Map<String, Object> ext = new HashMap(8);

  private String apiResult;

  public static ApiContext getContext() {
    ApiContext apiContext = ApiContextLocal.getInstance().get();
    return apiContext;
  }

  public static void release() {
    ApiContext ac = getContext();
    ac.setUserId(null);
    ac.setToken(null);
    ac.setApi(null);
    ac.getExt().clear();
    ac.setApiResult(null);
    ApiContextLocal.getInstance().remove();
  }

  public void delExt(String key) {
    this.getExt().remove(key);
  }

  public <T> T getExt(String key) {
    return (T) this.getExt().get(key);
  }

  public void setExt(String key, Object value) {
    this.getExt().put(key, value);
  }
}
