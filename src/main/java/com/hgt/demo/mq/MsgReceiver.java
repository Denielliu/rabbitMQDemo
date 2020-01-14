package com.hgt.demo.mq;

import com.hgt.demo.protomodel.PersonModel;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Title : MsgReceiver
 * @Description： 消息接收器
 * @Author ：柳强
 * @Date ：Created in 2020/1/13 19:19
 * @Modified By：
 * @Version: V 1.0
 * Copyright: Copyright (c) 2019
 * Company: 恒歌科技
 */
@Component("MsgReceiver")
public class MsgReceiver implements ChannelAwareMessageListener {


    @Override
    @RabbitListener(
            bindings = @QueueBinding(
                    // 消息队列
                    value = @Queue(value = "test_mq", durable = "false"),
                    // 通道
                    exchange = @Exchange(name = "out_exchange", durable = "true", type = "topic"),
                    // 路由主键
                    key = "test_mq"
            )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        byte[] msg = message.getBody();
        // 将数据存入数据库
        if (msg.length > 0) {
            PersonModel.Person p2 = PersonModel.Person.parseFrom(msg);
            System.out.println("after id:" + p2.getId());
            System.out.println("after name:" + p2.getName());
            System.out.println("after email:" + p2.getEmail());
        }
    }
}
