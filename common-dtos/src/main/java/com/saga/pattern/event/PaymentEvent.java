package com.saga.pattern.event;

import com.saga.pattern.dto.PaymentRequestDto;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
public class PaymentEvent implements Event {

    private UUID paymentId = UUID.randomUUID();
    private Date paymentDate = new Date();

    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;

    public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }

    @Override
    public UUID getEventId() {
        return paymentId;
    }

    @Override
    public Date getDate() {
        return paymentDate;
    }
}
