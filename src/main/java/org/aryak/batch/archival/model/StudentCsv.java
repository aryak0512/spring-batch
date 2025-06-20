package org.aryak.batch.archival.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentCsv {

    private long id;
    private String name;
    private String email;

}
