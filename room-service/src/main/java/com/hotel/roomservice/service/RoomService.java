package com.hotel.roomservice.service;

import com.hotel.roomservice.model.Room;
import com.hotel.roomservice.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    public Room createRoom(Room room) {
        if (roomRepository.existsByRoomNumber(room.getRoomNumber())) {
            throw new RuntimeException("Room number already exists: " + room.getRoomNumber());
        }
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room updatedRoom) {
        Room existing = getRoomById(id);
        existing.setRoomNumber(updatedRoom.getRoomNumber());
        existing.setRoomType(updatedRoom.getRoomType());
        existing.setPricePerNight(updatedRoom.getPricePerNight());
        existing.setStatus(updatedRoom.getStatus());
        existing.setDescription(updatedRoom.getDescription());
        return roomRepository.save(existing);
    }

    public void deleteRoom(Long id) {
        getRoomById(id);
        roomRepository.deleteById(id);
    }

    public List<Room> getRoomsByStatus(String status) {
        return roomRepository.findByStatus(status);
    }

    public List<Room> getRoomsByType(String roomType) {
        return roomRepository.findByRoomType(roomType);
    }

}
