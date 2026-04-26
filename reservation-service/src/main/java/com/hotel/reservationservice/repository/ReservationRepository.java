package com.hotel.reservationservice.repository;

import com.hotel.reservationservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByGuestId(Long guestId);

    List<Reservation> findByRoomId(Long roomId);

    List<Reservation> findByStatus(String status);

}
