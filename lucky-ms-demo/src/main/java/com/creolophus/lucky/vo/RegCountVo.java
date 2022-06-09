package com.creolophus.lucky.vo;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author magicnana
 * @date 2022/3/3 20:47
 */
@Data
@AllArgsConstructor
public class RegCountVo extends AbstractVo {

  private Integer all;
  private Integer winggo;
  private Integer winggoIos;
  private Integer winggoAndroid;

  private Integer onlyone;
  private Integer onlyoneIos;
  private Integer onlyoneAndroid;

  private Integer dudu;
  private Integer duduIos;
  private Integer duduAndroid;

}
