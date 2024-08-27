package com.saga.pattern.order;

import com.saga.pattern.dto.OrderRequestDto;
import com.saga.pattern.event.OrderStatus;
import com.saga.pattern.event.PaymentStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class OrderStatusUpdateHandler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public void updateOrder(int id, Consumer<PurchaseOrder> consumer){
        orderRepository.findById(id).ifPresent(consumer.andThen(this::updateOrder));
    }

    private void updateOrder(PurchaseOrder purchaseOrder) {
        boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
        OrderStatus orderStatus = isPaymentComplete ? OrderStatus.ORDER_COMPLETED : OrderStatus.ORDER_CANCELLED;

        purchaseOrder.setOrderStatus(orderStatus);

        if(!isPaymentComplete){
            orderStatusPublisher.publishOrderEvents(convertEntityToDto(purchaseOrder), orderStatus);
        }
    }

    public OrderRequestDto convertEntityToDto(PurchaseOrder purchaseOrder){
        return OrderRequestDto.builder().amount(purchaseOrder.getPrice())
                .orderId(purchaseOrder.getId())
                .userId(purchaseOrder.getUserId())
                .productId(purchaseOrder.getProductId())
                .build();
    }
}
