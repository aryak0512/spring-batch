package org.aryak.batch.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.config.job.ClientJobBuilder;
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
    private final ClientMetadata clientMetadata;
    private final ClientService clientService;

    @PostConstruct
    public void load() {
        
        // process each client and load its metadata
        clientService.fetchAll()
                .parallelStream()
                .forEach(c -> {

                    if (c.isEnabled()) {
                        clientMetadata.addOrUpdateClientConfig(c.getId(), c);
                        clientMetadata.addOrUpdateClientJob(c.getId(), jobBuilder.buildJob(c));
                    } else {
                        log.info("Client id : {} is not enabled. Will not load its metadata.", c.getId());
                    }

                });

        log.info("Job map : {}", clientMetadata.getClientJobs().size());
        log.info("Config map : {}", clientMetadata.getClientConfigs().size());
    }

}
