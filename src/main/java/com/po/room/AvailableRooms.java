package com.po.room;

import com.po.common.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class AvailableRooms {

    Period period;
    List<RoomView> rooms;

    public String toString(){
        String out = String.format("Rooms available within period %s \n",period.toString());
        for(int i =0; i< rooms.size(); i++){
            out = out.concat(rooms.get(i).toString()).concat("\n");
        }
        return out;
    }

}
