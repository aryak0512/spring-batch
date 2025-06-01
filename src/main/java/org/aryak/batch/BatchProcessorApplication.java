package org.aryak.batch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BatchProcessorApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(BatchProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
