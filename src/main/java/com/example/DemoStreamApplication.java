package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@EnableBinding(Processor.class)
@MessageEndpoint
public class DemoStreamApplication {

    @ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
    public Message<String> out(Message<String> in) {
        return MessageBuilder
                .withPayload("{" + in.getPayload() + "}")
                .copyHeadersIfAbsent(in.getHeaders())
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoStreamApplication.class, args);
    }
}
