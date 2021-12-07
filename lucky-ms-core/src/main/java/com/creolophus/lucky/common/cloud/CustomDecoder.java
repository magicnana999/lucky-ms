package com.creolophus.lucky.common.cloud;

import com.creolophus.lucky.common.json.GsonUtil;
import com.creolophus.lucky.common.json.JSON;
import com.creolophus.lucky.common.web.ApiResult;
import feign.Response;
import feign.codec.Decoder;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author magicnana
 * @date 2018/12/28 下午6:38
 */
public class CustomDecoder extends Decoder.Default implements Decoder {

  private static final Logger logger = LoggerFactory.getLogger(CustomDecoder.class);

  /** 不支持 char，byte[]，Class，ParameterizedType，GenericArrayType，TypeVariable */
  @Override
  public Object decode(Response response, Type type) throws IOException {

    String jsonString = (String) super.decode(response, String.class);

    ApiResult apiResult = JSON.parseObject(jsonString, ApiResult.class);

    Object ret = apiResult.getData();

    if (String.class.equals(type)) {
      return ret;
    } else if (Boolean.class.equals(type) || boolean.class.equals(type)) {
      return Boolean.valueOf(String.valueOf(ret));
    } else if (Byte.class.equals(type) || byte.class.equals(type)) {
      return Byte.valueOf(String.valueOf(ret));
    } else if (Double.class.equals(type) || double.class.equals(type)) {
      return Double.valueOf(String.valueOf(ret));
    } else if (Float.class.equals(type) || float.class.equals(type)) {
      return Float.valueOf(String.valueOf(ret));
    } else if (Integer.class.equals(type) || int.class.equals(type)) {
      return Integer.valueOf(String.valueOf(ret));
    } else if (Long.class.equals(type) || long.class.equals(type)) {
      return Long.valueOf(String.valueOf(ret));
    } else if (Short.class.equals(type) || short.class.equals(type)) {
      return Short.valueOf(String.valueOf(ret));
    } else if (List.class.equals(type)) {
      return ret;
    } else if (Map.class.equals(type)) {
      return ret;
    } else {
      Object obj = GsonUtil.toJava(ret, type);
      return obj;
    }
  }
}
