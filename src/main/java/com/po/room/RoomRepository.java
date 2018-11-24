package com.po.room;

import com.po.db.room.Room;
import com.po.common.Period;

import java.util.List;

public interface RoomRepository {

    void save(Room room);

    void delete(String roomNumber);

    List<RoomView> getAvailableRooms(Period period);

    void updateRoomAvailability(int roomId);

}
