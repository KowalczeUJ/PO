package com.po.db.user

import spock.lang.Specification
import spock.lang.Unroll

import javax.persistence.AttributeConverter

class UserTypeConverterSpec extends Specification {

    @Unroll
    def "should convert user type '#type' to '#expectedValue'"() {
        given:
            AttributeConverter<UserType, Integer> converter = new UserTypeConverter()
        when:
            def value = converter.convertToDatabaseColumn(type)
        then:
            value == expectedValue
        where:
            type                    || expectedValue
            null                    || null
            UserType.BASIC          || 2
            UserType.RECEPTIONIST   || 1
            UserType.ADMIN          || 0
    }

    @Unroll
    def "should convert value = '#value' to room type '#expectedRoomType'"() {
        given:
            AttributeConverter<UserType, Integer> converter = new UserTypeConverter()
        when:
            UserType userType = converter.convertToEntityAttribute(value)
        then:
            userType == expectedRoomType
        where:
            value   || expectedRoomType
            null    || null
            0       || UserType.ADMIN
            1       || UserType.RECEPTIONIST
            2       || UserType.BASIC
    }

}
