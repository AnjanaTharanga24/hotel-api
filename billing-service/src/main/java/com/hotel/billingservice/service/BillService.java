package com.hotel.billingservice.service;

import com.hotel.billingservice.model.Bill;
import com.hotel.billingservice.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));
    }

    public Bill getBillByReservationId(Long reservationId) {
        return billRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new RuntimeException("Bill not found for reservation: " + reservationId));
    }

    public Bill createBill(Bill bill) {
        bill.setPaymentStatus("PENDING");
        bill.setPaidAmount(0.0);
        return billRepository.save(bill);
    }

    public Bill makePayment(Long id, Double amount, String paymentMethod) {
        Bill bill = getBillById(id);
        bill.setPaidAmount(amount);
        bill.setPaymentMethod(paymentMethod);
        bill.setPaymentDate(LocalDate.now());
        if (amount >= bill.getTotalAmount()) {
            bill.setPaymentStatus("PAID");
        } else {
            bill.setPaymentStatus("PARTIAL");
        }
        return billRepository.save(bill);
    }

    public List<Bill> getBillsByStatus(String status) {
        return billRepository.findByPaymentStatus(status);
    }

}
