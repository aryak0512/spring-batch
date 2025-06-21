package org.aryak.batch.model;

import lombok.Data;

import java.util.Map;

/**
 * Data contract which will be passed from reader to processor
 */
@Data
public class InputRecord {

    private Map<String, String> data;
    private Long clientId;
}
