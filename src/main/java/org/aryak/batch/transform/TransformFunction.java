package org.aryak.batch.transform;

import java.util.Map;

@FunctionalInterface
public interface TransformFunction {
    String apply(String input, Map<String, String> context);
}

