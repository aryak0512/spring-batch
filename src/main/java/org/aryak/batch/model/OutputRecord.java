package org.aryak.batch.model;

import lombok.Data;

@Data
public class OutputRecord {

    private String val1;
    private String val2;
    private String val3;
    private NestedRecord nested1;
}
