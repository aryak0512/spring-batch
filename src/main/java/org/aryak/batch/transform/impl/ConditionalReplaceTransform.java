package org.aryak.batch.transform.impl;

import org.aryak.batch.transform.TransformFunction;

import java.util.Map;
import java.util.function.Predicate;

public class ConditionalReplaceTransform implements TransformFunction {
    private final Predicate<Map<String, String>> condition;
    private final String replacement;

    public ConditionalReplaceTransform(Predicate<Map<String, String>> condition, String replacement) {
        this.condition = condition;
        this.replacement = replacement;
    }

    @Override
    public String apply(String input, Map<String, String> context) {
        return condition.test(context) ? replacement : input;
    }
}

