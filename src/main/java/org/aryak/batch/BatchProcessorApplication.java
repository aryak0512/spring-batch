package org.aryak.batch;

import org.aryak.batch.config.DynamicBeanRegistrar;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BatchProcessorApplication implements CommandLineRunner {

    DynamicBeanRegistrar registrar;

    public static void main(String[] args) {
        SpringApplication.run(BatchProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //registrar.registerClientReader("client1", 1, ";", "/mnt/byos", List.of("c1", "c2"));


    }

}
