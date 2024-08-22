package com.saga.pattern.dto;

import com.saga.pattern.event.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Integer userId;
    private Integer productId;
    private Integer amount;
    private Integer orderId;
    private OrderStatus orderStatus;
}
