package org.aryak.batch.archival.transform;

import java.util.Map;

@FunctionalInterface
public interface TransformFunction {
    String apply(String input, Map<String, String> context);
}

