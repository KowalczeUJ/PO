package com.po.room.repository;

import com.po.db.room.Room;
import com.po.common.Period;
import com.po.room.RoomData;
import com.po.room.RoomView;

import java.util.List;

public interface RoomRepository {

    void save(Room room);

    void delete(String roomNumber);

    List<RoomView> getAvailableRooms(Period period);

    RoomData getRoomDataForId(int roomId);

    List<RoomView> getAllRooms();

}
