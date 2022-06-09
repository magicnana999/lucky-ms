package com.creolophus.lucky.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author magicnana
 * @date 2022/3/3 21:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieItemVo extends AbstractVo {

  @ApiModelProperty("国家编码(英文字母编码2)")
  private String country;

  @ApiModelProperty("数量")
  private String c;

  @ApiModelProperty("国家编码(中文)")
  private String cnName;

  @ApiModelProperty("国家编码(英文)")
  private String enName;

}
