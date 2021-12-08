package com.creolophus.lucky.common.web;

import com.creolophus.lucky.common.context.ApiContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Type;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;

/**
 * @author magicnana
 * @date 2020/12/16 5:39 PM
 */
public class LiuyiMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

  @Override
  public boolean canRead(
      Type type, @Nullable Class<?> contextClass, @Nullable MediaType mediaType) {

    Api api = (Api) ApiContext.getContext().getApi();

    if (api == null
        || StringUtils.isBlank(api.scope())
        || !Api.SCOPE_INTERNAL.equalsIgnoreCase(api.scope())) {
      return super.canRead(type, contextClass, mediaType);
    } else {
      return false;
    }
  }

  @Override
  public boolean canWrite(Class<?> clazz, MediaType mediaType) {
    Api api = (Api) ApiContext.getContext().getApi();
    if (api == null
        || StringUtils.isBlank(api.scope())
        || !Api.SCOPE_INTERNAL.equalsIgnoreCase(api.scope())) {
      return super.canWrite(clazz, mediaType);
    } else {
      return false;
    }
  }
}
