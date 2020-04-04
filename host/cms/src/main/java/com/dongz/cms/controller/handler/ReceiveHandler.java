package com.dongz.cms.controller.handler;

import com.dongz.cms.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dong
 * @date 2020/4/4 20:15
 * @desc
 */
@Component
public class ReceiveHandler {
    //监听email队列
    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_EMAIL})
    public void receive_email(String msg, Message message, Channel channel){
        System.out.println(msg);
    }
    //监听sms队列
    @RabbitListener(queues = {RabbitConfig.QUEUE_INFORM_SMS})
    public void receive_sms(String msg, Message message, Channel channel){
        System.out.println(msg);
    }
}
