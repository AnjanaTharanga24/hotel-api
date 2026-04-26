package com.hotel.billingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long reservationId;

    @Column(nullable = false)
    private Double totalAmount;

    private Double paidAmount;

    @Column(nullable = false)
    private String paymentStatus;   // PENDING, PAID, PARTIAL

    private String paymentMethod;   // CASH, CARD, ONLINE

    private LocalDate paymentDate;

}
