package com.hotel.reservationservice.service;

import com.hotel.reservationservice.model.Reservation;
import com.hotel.reservationservice.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + id));
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setStatus("PENDING");
        return reservationRepository.save(reservation);
    }

    public Reservation updateStatus(Long id, String status) {
        Reservation existing = getReservationById(id);
        existing.setStatus(status);
        return reservationRepository.save(existing);
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Reservation existing = getReservationById(id);
        existing.setRoomId(updatedReservation.getRoomId());
        existing.setGuestId(updatedReservation.getGuestId());
        existing.setCheckInDate(updatedReservation.getCheckInDate());
        existing.setCheckOutDate(updatedReservation.getCheckOutDate());
        existing.setStatus(updatedReservation.getStatus());
        existing.setTotalAmount(updatedReservation.getTotalAmount());
        return reservationRepository.save(existing);
    }

    public void deleteReservation(Long id) {
        getReservationById(id);
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getReservationsByGuest(Long guestId) {
        return reservationRepository.findByGuestId(guestId);
    }

    public List<Reservation> getReservationsByStatus(String status) {
        return reservationRepository.findByStatus(status);
    }

}
