package org.aryak.batch.archival.transform.impl;

import org.aryak.batch.archival.transform.TransformFunction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DateFormatTransform implements TransformFunction {
    private final DateTimeFormatter inputFormat;
    private final DateTimeFormatter outputFormat;

    public DateFormatTransform(String inFmt, String outFmt) {
        this.inputFormat = DateTimeFormatter.ofPattern(inFmt);
        this.outputFormat = DateTimeFormatter.ofPattern(outFmt);
    }

    @Override
    public String apply(String input, Map<String, String> context) {
        LocalDate date = LocalDate.parse(input, inputFormat);
        return outputFormat.format(date);
    }
}

