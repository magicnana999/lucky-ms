package com.creolophus.lucky.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author magicnana
 * @date 2022/3/3 17:51
 */
@Data
@AllArgsConstructor
public class SyncRegister extends DashboardBody {

  private String appKey;
  private String device;

}
