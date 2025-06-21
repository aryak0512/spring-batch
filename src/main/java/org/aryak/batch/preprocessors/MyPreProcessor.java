package org.aryak.batch.preprocessors;

import org.aryak.batch.archival.model.OutputRecord;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyPreProcessor implements ItemProcessor<Map<String, String>, OutputRecord> {
    
    @Override
    public OutputRecord process(Map<String, String> item) throws Exception {

        if (1 == 2) {
            // filtered!!
            return null;
        }

        return new OutputRecord();
    }
}
