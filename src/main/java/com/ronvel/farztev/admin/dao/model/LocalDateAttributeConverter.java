package com.ronvel.farztev.admin.dao.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate locDateTime) {
        return (locDateTime == null ? null : java.util.Date.from(locDateTime.atStartOfDay()
        	      .atZone(ZoneId.systemDefault())
        	      .toInstant()));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return (date == null ? null : Instant.ofEpochMilli(date.getTime())
      	      .atZone(ZoneId.systemDefault())
      	      .toLocalDate());
    }
}