package org.aryak.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * The client master table definition, metadata structure with some sensible defaults
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientConfig {

    private Long id;
    private String clientName;
    private String clientId = UUID.randomUUID().toString();
    private String filePath;
    private String delimiter = ",";
    private List<String> columnNames;
    private int linesToSkip = 1;
    private int chunkSize = 10;
    private int maxRetries = 1;
    private int skipLimit = 25;
}

