package com.creolophus.lucky.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author magicnana
 * @date 2022/3/3 17:51
 */
@Data
@AllArgsConstructor
public class TotalPanel extends DashboardBody {

  private Integer total,
      wingGoGrows,
      onlyOneGrows,
      duduGrows;

  public boolean isValid(){
    return total!=null && wingGoGrows!=null && onlyOneGrows!=null && duduGrows!=null;
  }
}
