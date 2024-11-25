package kz.narxoz.rabbit.rabbitreceiver.config;

import kz.narxoz.rabbit.rabbitreceiver.dto.OrderDTO;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        messageConverter.setClassMapper(classMapper());
        return messageConverter;
    }

    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("kz.narxoz.rabbit.rabbitreceiver.dto.OrderDTO", OrderDTO.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

    @Bean
    public Queue almatyQueue() {
        return new Queue("order.almaty");
    }

    @Bean
    public Queue astanaQueue() {
        return new Queue("order.astana");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("order-topic-exchange");
    }

    @Bean
    public Binding almatyBinding(Queue almatyQueue, TopicExchange topicExchange) {
        return new Binding("order.almaty", Binding.DestinationType.QUEUE, topicExchange.getName(), "order.almaty", null);
    }

    @Bean
    public Binding astanaBinding(Queue astanaQueue, TopicExchange topicExchange) {
        return new Binding("order.astana", Binding.DestinationType.QUEUE, topicExchange.getName(), "order.astana", null);
    }
}
