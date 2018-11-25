package com.po.db.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "room")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number", length = 5, nullable = false)
    private String number;

    @Column(name = "floor", nullable = false)
    private int floor;

    @Column(name = "beds", nullable = false)
    private int beds;

    @Column(name = "type", nullable = false)
    @Convert(converter = RoomTypeConverter.class)
    private RoomType type;

    @Column(name = "price_per_night", precision = 6, scale = 2, nullable = false)
    private BigDecimal pricePerNight;

}
