package org.aryak.batch.writers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstWriter implements ItemWriter<Long> {

    @Override
    public void write(Chunk<? extends Long> chunk) throws Exception {
        log.info("Chunk retrieved : {}", chunk);
    }
}
