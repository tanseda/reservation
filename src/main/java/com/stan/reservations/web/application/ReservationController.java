package com.stan.reservations.web.application;

import com.stan.reservations.business.domain.RoomReservation;
import com.stan.reservations.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    //private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model) {

        /*
        Date date = null;
        if (dateString != null) {
            try{
                date= DATE_FORMAT.parse(dateString);
            } catch (ParseException e) {
                date = new Date();
            }
        } else {
            date =new Date();
        }

        List<RoomReservation> roomReservationList =this.reservationService.getRoomReservationsForDate(date);

        model.addAttribute("roomReservations", roomReservationList);
        return "reservations";

        */
        List<RoomReservation> roomReservationList =this.reservationService.getRoomReservationsForDate(dateString);
        model.addAttribute("roomReservations", roomReservationList);
        return "reservations";
    }
}


