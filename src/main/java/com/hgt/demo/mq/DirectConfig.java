package com.hgt.demo.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: DirectConfig
 * @Description:
 * @Author 柳强
 * @Date 2020/01/13/0013 18:55
 * @Version V0.1
 * @Modified By:
 * Copyright: Copyright (c) 2019
 * Company: 恒歌科技
 */
@Configuration
public class DirectConfig {

    /**
     * 声明一个队列 支持持久化.
     *
     * @return
     */
    public Queue directQueue() {
        return new Queue("test_mq", false); //队列名字，是否持久化
    }

    /**
     * 声明Direct交换机 支持持久化.
     *
     * @return
     */
    public DirectExchange directExchange() {
        return new DirectExchange("out_exchange", false, true);//交换器名称、是否持久化、是否自动删除
    }

    /**
     * 通过绑定键 将指定队列绑定到一个指定的交换机
     *
     * @param queue
     * @param exchange
     * @return
     */
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("test_mq");
    }
}
