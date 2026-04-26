package com.hotel.billingservice.controller;

import com.hotel.billingservice.model.Bill;
import com.hotel.billingservice.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BillController {

    private final BillService billService;

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        return ResponseEntity.ok(billService.getBillById(id));
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<Bill> getBillByReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(billService.getBillByReservationId(reservationId));
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        return ResponseEntity.status(HttpStatus.CREATED).body(billService.createBill(bill));
    }

    @PatchMapping("/{id}/payment")
    public ResponseEntity<Bill> makePayment(
            @PathVariable Long id,
            @RequestParam Double amount,
            @RequestParam String paymentMethod) {
        return ResponseEntity.ok(billService.makePayment(id, amount, paymentMethod));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Bill>> getBillsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(billService.getBillsByStatus(status));
    }

}
