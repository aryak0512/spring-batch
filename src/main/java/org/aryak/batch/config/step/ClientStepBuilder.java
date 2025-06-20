package org.aryak.batch.config.step;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.archival.model.OutputRecord;
import org.aryak.batch.model.Client;
import org.aryak.batch.processors.GenericProcessor;
import org.aryak.batch.readers.GenericMapReaderFactory;
import org.aryak.batch.utils.Util;
import org.aryak.batch.writers.GenericWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

/**
 * Bean that defines the steps of the job
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ClientStepBuilder {

    private final JobRepository jobRepository;
    private final GenericMapReaderFactory readerFactory;
    private final PlatformTransactionManager transactionManager;
    private final GenericProcessor processor;
    private final GenericWriter writer;
    private final Util util;

    /**
     * The step related configs. Ideally I think skip count must be dynamic e.g. 25% of the file data
     *
     * @param config the client master configuration
     * @return the step object
     */
    public Step buildStep(Client config) {

        String stepName = "step-" + config.getClientId();
        return new StepBuilder(stepName, jobRepository)
                .<Map<String, String>, OutputRecord>chunk(config.getChunkSize(), transactionManager)
                .reader(readerFactory.getOrCreateReader(config))
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(Exception.class)
                .skip(NullPointerException.class)
                .skipLimit(util.computeSkipLimit(config))
                .retry(Exception.class)
                .retryLimit(config.getMaxRetries())
                .build();
    }
}

