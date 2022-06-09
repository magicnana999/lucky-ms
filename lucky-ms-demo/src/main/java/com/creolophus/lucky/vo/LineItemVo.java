package com.creolophus.lucky.vo;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author magicnana
 * @date 2022/3/3 22:21
 */
@Data
@AllArgsConstructor
public class LineItemVo extends AbstractVo {

  private String name;
  private String h;
  private String m;
  private Integer total;
  private Integer winggo;
  private Integer onlyone;
  private Integer dudu;

}
