package org.aryak.batch.processors;

import org.aryak.batch.archival.model.OutputRecord;
import org.aryak.batch.archival.transform.utils.JooqUtil;
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

        // populate the target object
        JooqUtil.setNestedProperty(outputRecord, "x.y.z", item.get("k1"));

        return outputRecord;
    }
}
