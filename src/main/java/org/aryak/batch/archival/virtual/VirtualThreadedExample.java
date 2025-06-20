package org.aryak.batch.archival.virtual;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Timer;

import static org.aryak.batch.archival.virtual.Constants.*;

@Slf4j
@Component
public class VirtualThreadedExample {


    public static void main(String[] args) {

        // start the observer task
        PoolStatusObserver observer = new PoolStatusObserver();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(observer, 10, 10);

        DataConsumer dataConsumer = new DataConsumer(lineQueue);
        DataProducer dataProducer = new DataProducer(lineQueue, FILE_PATH);

        // start the consumers to poll from queue
        for (int i = 0; i < CONSUMER_THREADS; i++) {
            consumer.submit(dataConsumer);
        }

        // start the single threaded producer to push to queue
        dataProducer.run();

        sleep(5);
        log.info("Main done");
    }

    static void sleep(double seconds) {
        try {
            Thread.sleep((long) (1000 * seconds));
        } catch (Exception e) {
        }
    }


}
