package org.aryak.batch.v2;

import org.aryak.batch.model.OutputRecord;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GenericProcessor implements ItemProcessor<Map<String, String>, OutputRecord> {

    @Override
    public OutputRecord process(@NonNull Map<String, String> item) throws Exception {

        // mapping logic to output record, apply transformations here
        OutputRecord outputRecord = new OutputRecord();

        outputRecord.setVal1("jsh783wued");

        return outputRecord;
    }
}
