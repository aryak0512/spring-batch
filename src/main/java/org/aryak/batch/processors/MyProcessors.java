package org.aryak.batch.processors;

import org.aryak.batch.archival.model.OutputRecord;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyProcessors extends CompositeItemProcessor<Map<String, String>, OutputRecord> {

    @Override
    public OutputRecord process(Map<String, String> item) throws Exception {
        return super.process(item);
    }
}
