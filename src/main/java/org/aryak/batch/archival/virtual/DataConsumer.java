package org.aryak.batch.archival.virtual;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

import static org.aryak.batch.archival.virtual.VirtualThreadedExample.sleep;

@Slf4j
public class DataConsumer implements Runnable {

    private static final String POISON_PILL = "__EOF__"; // special marker
    private final BlockingQueue<String> lineQueue;

    public DataConsumer(BlockingQueue<String> lineQueue) {
        this.lineQueue = lineQueue;
    }

    @Override
    public void run() {

        log.info("Started data consumer.");

        String line;
        while (true) {
            try {
                if ((line = lineQueue.take()).equals(POISON_PILL)) break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            processLine(line);
        }


    }

    private void processLine(String line) {
        // Your processing logic here
        log.info("Processed line : {}", line);

        // simulate latency
        sleep(3);
    }
}
