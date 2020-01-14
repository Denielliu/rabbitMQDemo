package com.hgt.demo.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Title: Sender
 * @Description:
 * @Author 柳强
 * @Date 2020/01/13 19:03
 * @Version V0.1
 * @Modified By:
 * Copyright: Copyright (c) 2019
 * Company: 恒歌科技
 */
@Component
public class Sender {

    @Autowired
    public AmqpTemplate rabbitmqTemplate;

    public void send(byte[] byteArray) {
        System.out.println("发送消息：" + byteArray);
        rabbitmqTemplate.convertAndSend("test_mq", byteArray);
    }
}
