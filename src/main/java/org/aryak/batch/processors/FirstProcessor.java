package org.aryak.batch.processors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstProcessor implements ItemProcessor<Integer, Long> {

    @Override
    public Long process(Integer item) throws Exception {
        log.info("Inside processor");
        return item.longValue() + 40;
    }
}
