package com.hgt.demo.controller;

import com.hgt.demo.model.Person;
import com.hgt.demo.mq.Sender;
import com.hgt.demo.protomodel.PersonModel;
import com.hgt.demo.utils.JSONResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: RabbitmqController
 * @Description: rabbitmq测试接口
 * @Author 柳强
 * @Date 2020/01/13 19:02
 * @Version V0.1
 * @Modified By:
 * Copyright: Copyright (c) 2019
 * Company: 恒歌科技
 */
@RestController
@RequestMapping(value = "/rabbitmq")
@Api(tags = "rabbitmq测试接口")
public class RabbitmqController {

    @Autowired
    public Sender sender;

    @RequestMapping(value = "/sender", method = RequestMethod.POST)
    @ApiOperation(value = "发送数据", notes = "发送数据")
    @ApiImplicitParam(dataType = "Person", name = "p", value = "Person实体类", required = true, paramType = "body")
    public JSONResultUtil sender(@RequestBody Person p) {
        System.out.println("----------start----------");
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(p.getId());
        builder.setName(p.getName());
        builder.setEmail(p.getEmail());

        PersonModel.Person person = builder.build();
        System.out.println("before:" + person.toString());
        sender.send(person.toByteArray());
        return JSONResultUtil.ok("发送成功!!!" + person.toByteArray());
    }

}
