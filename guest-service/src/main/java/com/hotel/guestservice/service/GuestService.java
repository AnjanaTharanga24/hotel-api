package com.hotel.guestservice.service;

import com.hotel.guestservice.model.Guest;
import com.hotel.guestservice.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));
    }

    public Guest createGuest(Guest guest) {
        if (guestRepository.existsByPhone(guest.getPhone())) {
            throw new RuntimeException("Guest already exists with phone: " + guest.getPhone());
        }
        return guestRepository.save(guest);
    }

    public Guest updateGuest(Long id, Guest updatedGuest) {
        Guest existing = getGuestById(id);
        existing.setName(updatedGuest.getName());
        existing.setPhone(updatedGuest.getPhone());
        existing.setEmail(updatedGuest.getEmail());
        existing.setIdProof(updatedGuest.getIdProof());
        return guestRepository.save(existing);
    }

    public void deleteGuest(Long id) {
        getGuestById(id);
        guestRepository.deleteById(id);
    }

}
