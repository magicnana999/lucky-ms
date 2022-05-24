package com.creolophus.lucky.common.json;

import static org.junit.Assert.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import java.util.List;
import org.junit.Test;

/**
 * @author magicnana
 * @date 2022/5/17 19:57
 */
public class JsonTest {

  @Test
  public void foo(){
    String s = "[10000,10010]";
    List list = JacksonUtil.toJava(s,List.class);
    System.out.println(JacksonUtil.toJava(s, List.class));
  }


  /**
   * @JSONField(serialize = false) 不输出
   * 为空的属性不输出
   * emptyMap  输出 {}
   * emptyList 输出 []
   * 空串       输出 ""
   * 数值型 0   输出 0
   */
  @Test
  public void loggerTest(){
    JsonEntity entity = new JsonEntity();
    String json = JSON.toJSONString(entity,new FastJsonDesensitizeFilter());
    System.out.println(json);
  }

  /**
   * @Expose(serialize = false) 不输出
   * 为空的属性不输出
   * emptyMap  输出 {}
   * emptyList 输出 []
   * 空串       输出 ""
   * 数值型 0   输出 0
   */
  @Test
  public void feignEncodeTest(){
    JsonEntity entity = new JsonEntity();
    String json = GsonUtil.toJson(entity);
    System.out.println(json);
  }

  @Test
  public void feignDecodeTest(){
    String json = "{\"item2\":{\"name\":\"张三\",\"empty\":\"\",\"sensitive1\":\"START\",\"sensitive2\":\"HIDE\",\"sensitive3\":\"SHORTEN\",\"age\":100,\"zero\":0,\"i1\":100,\"i2\":0,\"price\":12.55555,\"discount\":12.55555,\"amount\":100.33333},\"array2\":[{\"name\":\"张三\",\"empty\":\"\",\"sensitive1\":\"START\",\"sensitive2\":\"HIDE\",\"sensitive3\":\"SHORTEN\",\"age\":100,\"zero\":0,\"i1\":100,\"i2\":0,\"price\":12.55555,\"discount\":12.55555,\"amount\":100.33333},{\"name\":\"张三\",\"empty\":\"\",\"sensitive1\":\"START\",\"sensitive2\":\"HIDE\",\"sensitive3\":\"SHORTEN\",\"age\":100,\"zero\":0,\"i1\":100,\"i2\":0,\"price\":12.55555,\"discount\":12.55555,\"amount\":100.33333}],\"list2\":[],\"list3\":[{\"name\":\"张三\",\"empty\":\"\",\"sensitive1\":\"START\",\"sensitive2\":\"HIDE\",\"sensitive3\":\"SHORTEN\",\"age\":100,\"zero\":0,\"i1\":100,\"i2\":0,\"price\":12.55555,\"discount\":12.55555,\"amount\":100.33333},{\"name\":\"张三\",\"empty\":\"\",\"sensitive1\":\"START\",\"sensitive2\":\"HIDE\",\"sensitive3\":\"SHORTEN\",\"age\":100,\"zero\":0,\"i1\":100,\"i2\":0,\"price\":12.55555,\"discount\":12.55555,\"amount\":100.33333}],\"map2\":{},\"map3\":{\"entity2\":{\"name\":\"张三\",\"empty\":\"\",\"sensitive1\":\"START\",\"sensitive2\":\"HIDE\",\"sensitive3\":\"SHORTEN\",\"age\":100,\"zero\":0,\"i1\":100,\"i2\":0,\"price\":12.55555,\"discount\":12.55555,\"amount\":100.33333},\"entity1\":{\"name\":\"张三\",\"empty\":\"\",\"sensitive1\":\"START\",\"sensitive2\":\"HIDE\",\"sensitive3\":\"SHORTEN\",\"age\":100,\"zero\":0,\"i1\":100,\"i2\":0,\"price\":12.55555,\"discount\":12.55555,\"amount\":100.33333}}}\n";
    JsonEntity entity = GsonUtil.toJava(json,JsonEntity.class);
    System.out.println(entity.toString());
  }

  /**
   * @JSONField(serialize = false) 不输出。 不要使用@JSONField(serialize = false)，这将导致缓存后属性丢失
   * 为空的属性不输出
   * emptyMap  输出 {}
   * emptyList 输出 []
   * 空串       输出 ""
   * 数值型 0   输出 0
   */
  @Test
  public void lettuceJsonTest(){
    GenericFastJsonRedisSerializer f = new GenericFastJsonRedisSerializer();
    String json = new String(f.serialize(new JsonEntity()));

    Object obj = f.deserialize(f.serialize(new JsonEntity()));
    System.out.println(json);
  }


  /**
   * @JsonIgnore 不输出
   * 为空的属性    输出
   * emptyMap     输出 {}
   * emptyList    输出 []
   * 空串          输出 ""
   * 数值型 0      输出 0
   * 数值型 Long   输出为字符串
   * 数值型 Int    原样输出
   * 数值型 float  原样输出
   * 数值型 double 原样输出
   * 数值型 BigDecimal 输出为小数
   */
  @Test
  public void jacksonDecodeTest(){
    String json = JacksonUtil.toJson(new JsonEntity());
    System.out.println(json);
  }




}