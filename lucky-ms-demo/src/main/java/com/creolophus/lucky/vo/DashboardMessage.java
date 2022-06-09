package com.creolophus.lucky.vo;


import com.creolophus.lucky.common.json.GsonUtil;
import com.creolophus.lucky.vo.DashboardHeader.CMD;
import lombok.Data;
import net.bytebuddy.utility.RandomString;

/**
 * @author magicnana
 * @date 2022/3/3 16:31
 */
@Data
public class DashboardMessage extends AbstractVo {

  private DashboardHeader header;
  private DashboardBody body;

  public static DashboardMessage error(DashboardHeader header,String message){
    DashboardMessage msg = new DashboardMessage();
    header.setCode(1);
    header.setMessage(message);
    msg.setHeader(header);
    return msg;
  }

  public static DashboardMessage error(DashboardHeader header,Exception e){
    DashboardMessage msg = new DashboardMessage();
    header.setCode(1);
    header.setMessage(e.getMessage());
    msg.setHeader(header);
    return msg;
  }

  public static DashboardMessage success(DashboardHeader header,DashboardBody body){
    DashboardMessage msg = new DashboardMessage();
    msg.setHeader(header);
    msg.setBody(body);
    return msg;
  }

  public static DashboardMessage create(CMD cmd, DashboardBody body){
    DashboardMessage msg = new DashboardMessage();
    DashboardHeader header = new DashboardHeader();
    header.setCmd(cmd.value);
    msg.setHeader(header);
    msg.setBody(body);
    return msg;
  }

  public static void main(String[] args){
    DashboardMessage msg = new DashboardMessage();
    DashboardHeader header = new DashboardHeader();
    header.setCmd(1);
    header.setSeq(RandomString.make());
    DashboardBody body = new DashboardBody();
    msg.setHeader(header);
    msg.setBody(body);
    System.out.println(GsonUtil.toJson(msg));
  }
}
