package com.hotel.roomservice.repository;

import com.hotel.roomservice.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByStatus(String status);

    List<Room> findByRoomType(String roomType);

    boolean existsByRoomNumber(String roomNumber);

}
