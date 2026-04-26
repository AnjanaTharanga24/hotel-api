package com.hotel.billingservice.repository;

import com.hotel.billingservice.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    Optional<Bill> findByReservationId(Long reservationId);

    List<Bill> findByPaymentStatus(String paymentStatus);

}
