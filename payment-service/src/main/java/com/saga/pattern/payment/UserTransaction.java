package com.saga.pattern.payment;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserTransaction {

    @Id
    private Integer orderId;

    private Integer userId;

    private Integer amount;

}
