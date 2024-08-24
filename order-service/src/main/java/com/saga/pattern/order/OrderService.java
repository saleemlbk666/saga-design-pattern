package com.saga.pattern.order;

import com.saga.pattern.dto.OrderRequestDto;
import com.saga.pattern.event.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
        PurchaseOrder order = orderRepository.save(convertDtoToEntity(orderRequestDto));
        orderRequestDto.setOrderId(order.getId());
        orderStatusPublisher.publishOrderEvents(orderRequestDto, OrderStatus.ORDER_CREATED);

        return order;
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    private PurchaseOrder convertDtoToEntity(OrderRequestDto orderRequestDto) {

        return PurchaseOrder.builder()
                .productId(orderRequestDto.getProductId())
                .userId(orderRequestDto.getUserId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .price(orderRequestDto.getAmount())
                .build();
    }
}
