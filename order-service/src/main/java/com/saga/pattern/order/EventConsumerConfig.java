package com.saga.pattern.order;

import com.saga.pattern.event.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfig {

    @Autowired
    private OrderStatusUpdateHandler orderStatusUpdateHandler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){

        return (payment) -> orderStatusUpdateHandler.updateOrder(payment.getPaymentRequestDto().getOrderId(), po-> {
            po.setPaymentStatus(payment.getPaymentStatus());
        });

    }
}
