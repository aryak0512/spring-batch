package org.aryak.batch.model;

import jakarta.persistence.*;
import lombok.*;
import org.aryak.batch.utils.StringListConverter;

import java.util.List;
import java.util.UUID;

/**
 * The client master table definition, metadata structure with some sensible defaults
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "client_master")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName = "UNKNOWN_CLIENT";
    private String clientId = UUID.randomUUID().toString();
    private String filePath;
    private String delimiter = ",";
    @Convert(converter = StringListConverter.class)
    private List<String> columnNames;
    private int linesToSkip = 1;
    private int chunkSize = 10;
    private int maxRetries = 1;
    private int skipLimit = 25;
    private boolean enabled;

    // can make List<CsvMapping> mappings here - need to explore it

}

