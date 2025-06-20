package org.aryak.batch.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.config.job.ClientJobBuilder;
import org.aryak.batch.config.job.ClientJobLauncher;
import org.aryak.batch.service.ClientService;
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
    private final ClientService clientService;

    @PostConstruct
    public void load() {
        // process each client and load its metadata
        clientService.fetchAll().parallelStream().forEach(c -> {
            brokerMetadata.addOrUpdateClientConfig(c);
            brokerMetadata.addOrUpdateClientJob(c.getClientId(), jobBuilder.buildJob(c));
        });

        log.info("Job map : {}", brokerMetadata.getClientJobs().size());
        log.info("Config map : {}", brokerMetadata.getClientConfigs().size());
    }

}
