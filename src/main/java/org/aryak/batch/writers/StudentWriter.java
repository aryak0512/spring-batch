package org.aryak.batch.writers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentWriter implements ItemWriter<String> {

    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {
        // write to queue
        log.info("I'm writing student json to queue!!");
    }
}
