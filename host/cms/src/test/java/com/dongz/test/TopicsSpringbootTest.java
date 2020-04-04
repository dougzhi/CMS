package com.dongz.test;

import com.dongz.cms.config.RabbitConfig;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dong
 * @date 2020/4/4 14:43
 * @desc
 */
@SpringBootTest(classes = TopicsSpringbootTest.class)
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.dongz.cms")
public class TopicsSpringbootTest {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Test
    public void testSendByTopics(){
        for (int i=0;i<5;i++){
            String message = "sms email inform to user"+i;
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",message);
            System.out.println("Send Message is:'" + message + "'");
        } }
}
