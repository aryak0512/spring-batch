package org.aryak.batch.model;

import lombok.Data;

import java.util.List;

@Data
public class NestedRecord {

    private String a;
    private String b;
    private List<InnerNested> innerNestedList;
}
