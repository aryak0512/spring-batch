package org.aryak.batch.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GenericWriter implements ItemWriter<OutputRecord> {

    int i = 1;

    @Override
    public void write(@NonNull Chunk<? extends OutputRecord> chunk) throws Exception {
        // write data to downstream system
        List<? extends OutputRecord> items = chunk.getItems();
        int size = items.size();
        log.info("Chunk no : {} of size : {} and data records : {}", i, size, items);
        i++;
    }
}
