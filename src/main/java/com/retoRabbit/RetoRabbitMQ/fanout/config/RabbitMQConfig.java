package com.retoRabbit.RetoRabbitMQ.fanout.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {
    @Bean
    Queue apartament101AQueue() {
        return new Queue("apartamento1AQueue", false);
    }
    @Bean
    Queue apartament102AQueue() {
        return new Queue("apartamento2AQueue", false);
    }
    @Bean
    Queue apartament103AQueue() {
        return new Queue("apartamento3AQueue", false);
    }
    @Bean
    Queue apartament104AQueue() {
        return new Queue("apartamento4AQueue", false);
    }
    @Bean
    Queue apartament105AQueue() {
        return new Queue("apartamento3AQueue", false);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    Binding apartament101ABinding(Queue apartament101AQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(apartament101AQueue).to(exchange);
    }
    @Bean
    Binding apartament102ABinding(Queue apartament102AQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(apartament102AQueue).to(exchange);
    }

    @Bean
    Binding apartament103ABinding(Queue apartament103AQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(apartament103AQueue).to(exchange);
    }
    @Bean
    Binding apartament104ABinding(Queue apartament104AQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(apartament104AQueue).to(exchange);
    }

    @Bean
    Binding apartament105ABinding(Queue apartament105AQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(apartament105AQueue).to(exchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        return simpleMessageListenerContainer;
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
