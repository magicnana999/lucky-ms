package com.creolophus.lucky.vo;


import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author magicnana
 * @date 2022/3/3 21:41
 */
@Data
@AllArgsConstructor
public class PieVo extends AbstractVo {

  @ApiModelProperty("总数")
  private Integer count;
  @ApiModelProperty("每个国家的数量")
  private List<PieItemVo> list = new ArrayList();


}
