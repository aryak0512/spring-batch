package org.aryak.batch.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        // some validation could be added here
        StringBuilder sb = new StringBuilder();
        if (strings != null) {
            strings.forEach(s -> sb.append(s).append(","));
            sb.deleteCharAt(sb.length() - 1); // remove last comma
        }
        return sb.toString();
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        // some validation could be added here
        return Arrays.stream(s.split(",")).toList();
    }
}
