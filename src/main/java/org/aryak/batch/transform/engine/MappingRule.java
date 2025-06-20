package org.aryak.batch.transform.engine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aryak.batch.transform.TransformFunction;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MappingRule {

    private String sourceKey;
    private String targetProperty;
    private List<TransformFunction> transformations;
}

