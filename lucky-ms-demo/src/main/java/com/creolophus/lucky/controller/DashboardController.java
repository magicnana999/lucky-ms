package com.creolophus.lucky.controller;

import com.creolophus.lucky.common.web.Api;
import com.creolophus.lucky.vo.PieVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 朝辞白帝彩云间 千行代码一日还 两岸领导啼不住 地铁已到回龙观
 *
 * @author magicnana
 * @date 2022/3/11 13:49
 */
@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_VALUE)
public class DashboardController {


  @ApiOperation(value = "WingGo饼图",response = PieVo.class)
  @GetMapping(value = "/winggo_pie")
  public String queryWingGoPie() {
    return "{\"count\":692,\"list\":[{\"country\":\"UN\",\"c\":\"548\",\"cnName\":\"其他\",\"enName\":\"UNKNOWN\"}"
        + ",{\"country\":\"AG\",\"c\":\"1\",\"cnName\":\"安提瓜和巴布达\",\"enName\":\"Antigua and Barbuda\"}]}";
  }

  @ApiOperation(value = "OnlyOne饼图",response = PieVo.class)
  @GetMapping(value = "/onlyone_pie")
  public String queryOnlyOnePie() {
    return "{\"count\":692,\"list\":[{\"country\":\"UN\",\"c\":\"548\",\"cnName\":\"其他\",\"enName\":\"UNKNOWN\"}"
        + ",{\"country\":\"AG\",\"c\":\"1\",\"cnName\":\"安提瓜和巴布达\",\"enName\":\"Antigua and Barbuda\"}]}";
  }

  @ApiOperation(value = "DUDU饼图",response = PieVo.class)
  @GetMapping(value = "/dudu_pie")
  public String queryDuduPie() {
    return "{\"count\":692,\"list\":[{\"country\":\"UN\",\"c\":\"548\",\"cnName\":\"其他\",\"enName\":\"UNKNOWN\"}"
        + ",{\"country\":\"AG\",\"c\":\"1\",\"cnName\":\"安提瓜和巴布达\",\"enName\":\"Antigua and Barbuda\"}]}";
  }

  @ApiOperation(value = "DUDU统计个数",notes = "device:[IOS,ANDROID]  begin:可以是timestamp,秒或者毫秒都行,也可以是Date")
  @GetMapping(value = "/dudu_count")
  public Integer queryDuduCount(
      @RequestParam(value = "device", required = false, defaultValue = "") String device,
      @RequestParam(value = "begin", required = false, defaultValue = "-1") Long begin,
      @RequestParam(value = "end", required = false, defaultValue = "-1") Long end) {
    return 100;
  }

  @ApiOperation(value = "WingGo统计个数",notes = "device:[IOS,ANDROID]  begin:可以是timestamp,秒或者毫秒都行,也可以是Date")
  @GetMapping(value = "/winggo_count")
  public Integer queryWinggoCount(
      @RequestParam(value = "device", required = false, defaultValue = "") String device,
      @RequestParam(value = "begin", required = false, defaultValue = "-1") Long begin,
      @RequestParam(value = "end", required = false, defaultValue = "-1") Long end) {
    return 100;
  }

  @ApiOperation(value = "OnlyOne统计个数",notes = "device:[IOS,ANDROID]  begin:可以是timestamp,秒或者毫秒都行,也可以是Date")
  @GetMapping(value = "/onlyone_count")
  public Integer queryOnlyOneCount(
      @RequestParam(value = "device", required = false, defaultValue = "") String device,
      @RequestParam(value = "begin", required = false, defaultValue = "-1") Long begin,
      @RequestParam(value = "end", required = false, defaultValue = "-1") Long end) {
    return 100;
  }

  @ApiOperation(value = "DUDU统计在线")
  @GetMapping(value = "/dudu_online")
  public Integer queryDuduOnline() {
    return 100;
  }

  @ApiOperation(value = "WingGo统计在线")
  @GetMapping(value = "/winggo_online")
  public Integer queryWingGoOnline() {
    return 100;
  }

  @ApiOperation(value = "OnlyOne统计在线")
  @GetMapping(value = "/onlyone_online")
  public Integer queryOnlyOneOnline() {
    return 100;
  }

}
