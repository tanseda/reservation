package com.stan.reservations.web.service;

import com.stan.reservations.business.domain.RoomReservation;
import com.stan.reservations.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ReservationServiceController {
    @Autowired
    ReservationService reservationService;

    @RequestMapping(method= RequestMethod.GET, value="/reservations/{date}")
    public List<RoomReservation> getAllReservationsForDate (@PathVariable (value = "date") String dateString) {
            return this.reservationService.getRoomReservationsForDate(dateString);
    }
}
