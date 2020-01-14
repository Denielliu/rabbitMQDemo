package com.hgt.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Title: Person
 * @Description: Person实体类
 * @Author 柳强
 * @Date 2020/01/13 19:04
 * @Version V0.1
 * @Modified By:
 * Copyright: Copyright (c) 2019
 * Company: 恒歌科技
 */
@Data
@ApiModel(description = "Person实体类")
public class Person {

    @ApiModelProperty(value = "id")
    public int id;

    @ApiModelProperty(value = "名称")
    public String name;

    @ApiModelProperty(value = "邮件")
    public String email;

}
