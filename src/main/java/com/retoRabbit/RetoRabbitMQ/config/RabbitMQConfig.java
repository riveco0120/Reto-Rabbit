package com.retoRabbit.RetoRabbitMQ.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private String routingKey = "queue.apartamenOdd ";

    @Bean
    Queue Apartment101Queue() {
        return new Queue("Apartment101", false);
    }

    @Bean
    Queue Apartment103Queue() {
        return new Queue("Apartment103", false);
    }

    @Bean
    Queue Apartment105Queue() {
        return new Queue("Apartment105", false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }


    @Bean
    Binding bindingApartame101(Queue apartamento1AQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamento1AQueue).to(topicExchange).with("queue.apartamenOdd");
    }

    @Bean
    Binding bindingApartame102(Queue apartamento3AQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamento3AQueue).to(topicExchange).with("queue.apartamenOdd");
    }


    @Bean
    Binding bindingApartame105(Queue apartamento3AQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamento3AQueue).to(topicExchange).with("queue.apartamenOdd");
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
