package com.gxweb.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "UserEntity", description = "用户对象")
@TableName(value = "swagger_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerUserEntity implements Serializable{
	private static final long serialVersionUID = 5237730257103305078L;

	@ApiModelProperty(value ="用户id",name="id",dataType="Long",required = false,example = "1",hidden = false )
	private Long id;

	@ApiModelProperty(value ="用户名",name="userName",dataType="String",required = false,example = "关羽" )
	private String userName;

	@ApiModelProperty(value ="用户性别",name="userSex",dataType="String",required = false,example = "男" )
	private String userSex;


}
