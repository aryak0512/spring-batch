package org.aryak.batch.processors;

import org.aryak.batch.archival.model.OutputRecord;
import org.aryak.batch.archival.transform.utils.JooqUtil;
import org.aryak.batch.model.InputRecord;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class GenericProcessor implements ItemProcessor<InputRecord, OutputRecord> {

    @Override
    public OutputRecord process(@NonNull InputRecord item) throws Exception {

        // mapping logic to output record, apply transformations here
        OutputRecord outputRecord = new OutputRecord();

        // populate the target object
        JooqUtil.setNestedProperty(outputRecord, "nested1.a", "");

        return outputRecord;
    }
}
