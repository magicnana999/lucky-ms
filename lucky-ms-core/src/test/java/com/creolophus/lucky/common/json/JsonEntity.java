package com.creolophus.lucky.common.json;

import static com.creolophus.lucky.common.json.Desensitized.Sensitive.HIDE;
import static com.creolophus.lucky.common.json.Desensitized.Sensitive.SHORTEN;
import static com.creolophus.lucky.common.json.Desensitized.Sensitive.STAR;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.apache.commons.collections.map.HashedMap;

/**
 * @author magicnana
 * @date 2022/5/17 19:57
 */
@Data
public class JsonEntity {

  private JsonItem item1;
  private JsonItem item2 = new JsonItem();

  @Data
  public static class JsonItem{
    @JSONField(serialize = false)
    @Expose(serialize = false)
    @JsonIgnore
    private String ignore = "ignore";
    private String name = "张三";
    private String empty = "";
    private String none;
    @Desensitized(type=STAR)
    private String sensitive1 = "START";
    @Desensitized(type=HIDE)
    private String sensitive2 = "HIDE";
    @Desensitized(type=SHORTEN)
    private String sensitive3 = "SHORTEN";
    private Long age = 100L;
    private Long zero = 0L;
    private Integer i1 = 100;
    private Integer i2 = 0;
    private Double price = 12.55555;
    private Float discount = 12.55555F;
    private BigDecimal amount = new BigDecimal("100.33333");
  }




  private JsonItem[] array1;
  private JsonItem[] array2 = new JsonItem[]{new JsonItem(), new JsonItem()};

  private List<JsonItem> list1;
  private List<JsonItem> list2 = Collections.EMPTY_LIST;
  private List<JsonItem> list3 = Arrays.asList(array2);

  private Map<String, JsonItem> map1;
  private Map<String, JsonItem> map2 = Collections.emptyMap();
  private Map<String, JsonItem> map3 = initMap3();

  private Map<String, JsonItem> initMap3() {
    Map<String, JsonItem> map = new HashedMap();
    map.put("entity1", new JsonItem());
    map.put("entity2", new JsonItem());
    return map;
  }



}
