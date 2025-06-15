package org.aryak.batch.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.model.ClientConfig;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class ClientStepBuilder {

    private final JobRepository jobRepository;
    private final GenericMapReaderFactory readerFactory;
    private final PlatformTransactionManager transactionManager;

    public Step buildStep(ClientConfig config) {
        return new StepBuilder("step-" + config.clientId(), jobRepository)
                .<Map<String, String>, Map<String, String>>chunk(config.chunkSize(), transactionManager)
                .reader(readerFactory.getOrCreateReader(config))
                .writer(items -> {
                    for (Map<String, String> item : items) {
                        log.info("Hi : {}", item);
                    }
                })
                .build();
    }
}

