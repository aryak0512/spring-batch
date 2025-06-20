package org.aryak.batch.archival.virtual;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Constants {

    public static final int CONSUMER_THREADS = 100;
    public static final int QUEUE_CAPACITY = 750; // Tune based on processing speed & memory
    public static final Path FILE_PATH = Paths.get("MOCK_DATA.csv");
    public static final ThreadPoolExecutor producer = new ThreadPoolExecutor(100, 100, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_CAPACITY));
    public static final AtomicInteger consumerThreadCount = new AtomicInteger(1);
    protected static final BlockingQueue<String> lineQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    static MyThreadFactory mtf = new MyThreadFactory("consumer");
    public static final ThreadPoolExecutor consumer = new ThreadPoolExecutor(100, 100, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(QUEUE_CAPACITY), mtf);

    private Constants() {
    }

}
