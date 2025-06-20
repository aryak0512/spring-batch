package org.aryak.batch.archival.model;

import lombok.Data;

import java.util.List;

@Data
public class NestedRecord {

    private String a;
    private String b;
    private List<InnerNested> innerNestedList;
}
