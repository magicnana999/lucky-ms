package com.creolophus.lucky.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author magicnana
 * @date 2022/3/3 17:51
 */
@Data
@AllArgsConstructor
public class WinggoPanel extends DashboardBody {

  private Integer total, grows, ios, android, iosGrows, androidGrows;

  public boolean isValid() {
    return total != null
        && grows != null
        && ios != null
        && android != null
        && iosGrows != null
        && androidGrows != null;
  }
}
