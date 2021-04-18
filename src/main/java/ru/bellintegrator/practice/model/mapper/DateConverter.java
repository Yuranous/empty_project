package ru.bellintegrator.practice.model.mapper;

import java.time.LocalDate;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class DateConverter extends CustomConverter<String, LocalDate> {

    @Override
    public LocalDate convert(String s, Type<? extends LocalDate> type, MappingContext mappingContext) {
       if (s == null) {
           return null;
       }
       return LocalDate.parse(s);
    }
}
