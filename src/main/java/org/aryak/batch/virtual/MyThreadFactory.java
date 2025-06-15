package org.aryak.batch.virtual;

import java.util.concurrent.ThreadFactory;

import static org.aryak.batch.virtual.Constants.consumerThreadCount;

public class MyThreadFactory implements ThreadFactory {

    private final String prefix;

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(prefix.concat("-" + consumerThreadCount.getAndIncrement()));
        t.setPriority(5);
        return t;
    }
}
