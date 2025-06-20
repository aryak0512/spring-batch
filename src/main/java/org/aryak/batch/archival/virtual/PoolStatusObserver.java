package org.aryak.batch.archival.virtual;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

import static org.aryak.batch.archival.virtual.Constants.consumer;

@Slf4j
public class PoolStatusObserver extends TimerTask {


    @Override
    public void run() {
        var activeCount = consumer.getActiveCount();
        var queueSize = consumer.getQueue().size();
        var completedTaskCount = consumer.getCompletedTaskCount();
        log.info("Active : {} Complete : {} Queue : {}", activeCount, completedTaskCount, queueSize);
    }
}
