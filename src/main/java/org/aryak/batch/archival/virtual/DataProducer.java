package org.aryak.batch.archival.virtual;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class DataProducer {

    private static final String POISON_PILL = "__EOF__"; // special marker
    private final BlockingQueue<String> lineQueue;
    private final Path filePath;

    public DataProducer(BlockingQueue<String> lineQueue, Path filePath) {
        this.lineQueue = lineQueue;
        this.filePath = filePath;
    }

    public void run() {

        try ( var lines = Files.lines(filePath) ) {
            lines.forEach(line -> {
                try {
                    lineQueue.put(line); // blocks if full
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Signal completion
            try {
                lineQueue.put(POISON_PILL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
