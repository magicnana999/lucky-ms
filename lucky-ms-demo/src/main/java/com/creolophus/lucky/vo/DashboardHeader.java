package com.creolophus.lucky.vo;


import lombok.Data;
import lombok.Getter;

/**
 * @author magicnana
 * @date 2022/3/3 16:29
 */
@Data
public class DashboardHeader extends AbstractVo {

  private String seq;
  private int code = 0;
  private String message;
  private int cmd;


  @Getter
  public enum CMD{
    TOTAL(1),
    WINGGO(2),
    ONLYONE(3),
    DUDU(4),
    SYNC_REGISTER(5),
    SYNC_ONLINE(6),
    ;
    int value;

    CMD(int value){
      this.value = value;
    }

    public static CMD valueOf(int cmd) {
      for(CMD c : CMD.values()){
        if(c.value==cmd){
          return c;
        }
      }
      return null;
    }
  }

}
