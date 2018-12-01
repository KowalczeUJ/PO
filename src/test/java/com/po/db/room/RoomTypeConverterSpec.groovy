package com.po.db.room

import spock.lang.Specification
import spock.lang.Unroll

import javax.persistence.AttributeConverter

class RoomTypeConverterSpec extends Specification {

    @Unroll
    def "should convert room type '#type' to '#expectedValue'"() {
        given:
            AttributeConverter<RoomType, Integer> converter = new RoomTypeConverter()
        when:
            def value = converter.convertToDatabaseColumn(type)
        then:
            value == expectedValue
        where:
            type                || expectedValue
            null                || null
            RoomType.STUDIO     || 0
            RoomType.DELUXE     || 1
            RoomType.PREMIER    || 2
            RoomType.PENTHOUSE  || 3
    }

    @Unroll
    def "should convert value = '#value' to room type '#expectedRoomType'"() {
        given:
            AttributeConverter<RoomType, Integer> converter = new RoomTypeConverter()
        when:
            RoomType roomType = converter.convertToEntityAttribute(value)
        then:
            roomType == expectedRoomType
        where:
            value   || expectedRoomType
            null    || null
            0       || RoomType.STUDIO
            1       || RoomType.DELUXE
            2       || RoomType.PREMIER
            3       || RoomType.PENTHOUSE
    }
}