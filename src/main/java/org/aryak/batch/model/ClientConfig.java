package org.aryak.batch.model;

import java.util.List;

// job could also be added here?
public record ClientConfig(
        String clientId,
        String filePath,
        String delimiter,
        List<String> columnNames,
        int linesToSkip,
        int chunkSize
) {
}
