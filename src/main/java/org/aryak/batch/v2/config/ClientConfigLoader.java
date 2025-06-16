package org.aryak.batch.v2.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.v2.ClientJobBuilder;
import org.aryak.batch.v2.ClientJobLauncher;
import org.aryak.batch.v2.utils.Util;
import org.springframework.stereotype.Component;


/**
 * Thread that runs at app startup and populates all client job definitions by pulling metadata from database
 */
@Component
@Slf4j
@AllArgsConstructor
public class ClientConfigLoader {

    private final ClientJobBuilder jobBuilder;
    private final ClientJobLauncher jobLauncher;
    private final BrokerMetadata brokerMetadata;

    @PostConstruct
    public void load() {

        log.info("Post construct called - loading job metadata for all brokers!");
        Util.getClientConfigs().parallelStream().forEach(c -> {
            // process each client and load its metadata
            brokerMetadata.addOrUpdateClientConfig(c);
            brokerMetadata.addOrUpdateClientJob(c.clientId(), jobBuilder.buildJob(c));
        });

        log.info("Job map : {}", brokerMetadata.getClientJobs().size());
        log.info("Config map : {}", brokerMetadata.getClientConfigs().size());

        jobLauncher.runJob("c1");
    }

}
