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
    Queue apartament101AQueue() {
        return new Queue("apartamento1AQueue", false);
    }

    @Bean
    Queue apartament103AQueue() {
        return new Queue("apartamento3AQueue", false);
    }

    @Bean
    Queue apartament105AQueue() {
        return new Queue("apartamento3AQueue", false);
    }

    @Bean
    Queue apartamentAll() {
        return new Queue("apartamentAllAQueue", false);
    }


    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    Binding apartament101ABinding(Queue apartament101AQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartament101AQueue).to(topicExchange).with(routingKey);
    }

    @Bean
    Binding apartament103ABinding(Queue apartament103AQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartament103AQueue).to(topicExchange).with(routingKey);
    }

    @Bean
    Binding apartament105ABinding(Queue apartament105AQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartament105AQueue).to(topicExchange).with(routingKey);
    }

    @Bean
    Binding apartamentAll(Queue apartamentAll, TopicExchange topicExchange) {
        return BindingBuilder.bind(apartamentAll).to(topicExchange).with("queue.*");
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
