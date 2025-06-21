package org.aryak.batch.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * POJO for csv file mappings - the mapping_master table
 */
@Entity
@Table(name = "mapping_master")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CsvMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String destination;

    // will connect this later
    private Long clientId;
}
