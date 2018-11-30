package com.stan.reservations.data.repository;

import com.stan.reservations.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {  //Value, Key in table

    Room findByNumber(String number);

    Room findAllByNameOrderByNumber(String name);
}
