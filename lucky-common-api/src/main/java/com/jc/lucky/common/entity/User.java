package com.jc.lucky.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author MyBatis
 * @since 2021-10-20
 */
@Data
@EqualsAndHashCode
@TableName("user")
@ApiModel(value="User对象", description="")
public class User implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "状态：1启用，0禁用")
    private Integer status;

    @ApiModelProperty(value = "用户账号")
    private String username;

    private String firstName;

    private String lastName;

    @ApiModelProperty(value = "1为男，2为女，3为未知")
    private Integer gender;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "盐值")
    private String salt;

    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "国家编码")
    private String countryCode;

    @ApiModelProperty(value = "创建时间")
    private Integer createTime;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "谷歌ID")
    private String gooleKey;

    @ApiModelProperty(value = "脸书ID")
    private String facebookKey;

    @ApiModelProperty(value = "apple ID")
    private String appleKey;

    @ApiModelProperty(value = "1:live(直播)")
    private Integer role;

    @ApiModelProperty(value = "国家名称")
    private String countryName;

    @ApiModelProperty(value = "城市编码")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;


}
