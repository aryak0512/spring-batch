package org.aryak.batch.preprocessors;

import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.model.InputRecord;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyPreProcessor implements ItemProcessor<InputRecord, InputRecord> {

    @Override
    public InputRecord process(InputRecord item) throws Exception {

        if (1 == 2) {
            // filtered!!
            return null;
        }

        log.info("Pre processor received record : {}", item);
        return item;
    }
}
