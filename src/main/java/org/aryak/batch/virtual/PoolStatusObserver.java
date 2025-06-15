package org.aryak.batch.virtual;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

import static org.aryak.batch.virtual.Constants.consumer;

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
