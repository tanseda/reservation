package com.stan.reservations.business.service;

import com.stan.reservations.business.domain.RoomReservation;
import com.stan.reservations.data.entity.Guest;
import com.stan.reservations.data.entity.Reservation;
import com.stan.reservations.data.entity.Room;
import com.stan.reservations.data.repository.GuestRepository;
import com.stan.reservations.data.repository.ReservationRepository;
import com.stan.reservations.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservationService {
    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(String dateString){
        Date date = this.createDateFromDateString(dateString);

        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();

        rooms.forEach(room-> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));

        if(reservations!= null) {
            reservations.forEach(reservation -> {
                Optional<Guest> guestOptional = guestRepository.findById(reservation.getGuestId());

                if (guestOptional.isPresent()) {

                    Guest guest = guestOptional.get();
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());

                    roomReservation.setGuestId(guest.getId());
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setDate(date);
                }
            });
        }
        List<RoomReservation> roomReservations = new ArrayList<>();
        for(Long roomId : roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(roomId));
        }

        return roomReservations;
    }


    private Date createDateFromDateString(String dateString){
        Date date = null;
        if(null!=dateString) {
            try {
                date = DATE_FORMAT.parse(dateString);
            }catch(ParseException pe){
                date = new Date();
            }
        }else{
            date = new Date();
        }
        return date;
    }
}
