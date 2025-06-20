package org.aryak.batch.archival.transform.engine;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.aryak.batch.archival.transform.TransformFunction;
import org.aryak.batch.archival.transform.utils.AutoPopulatingBeanUtils;

import java.util.List;
import java.util.Map;

@Slf4j
public class MappingEngine {

    private final List<MappingRule> rules;

    public MappingEngine(List<MappingRule> rules) {
        this.rules = rules;
    }

    public void map(Map<String, String> input, Object targetObject) throws Exception {
        for (MappingRule rule : rules) {

            String value = input.get(rule.getSourceKey());
            log.info("Map key : {} and value extracted : {}", rule.getSourceKey(), value);
            for (TransformFunction fn : rule.getTransformations()) {
                value = fn.apply(value, input);
            }
            log.info("value modified : {}", value);
            BeanUtilsBean beanUtils = new AutoPopulatingBeanUtils();
            beanUtils.setProperty(targetObject, "nested1.a", value);
        }

    }
}

