package org.aryak.batch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.config.BrokerMetadata;
import org.aryak.batch.config.job.ClientJobBuilder;
import org.aryak.batch.config.job.ClientJobLauncher;
import org.aryak.batch.model.Client;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/clients")
public class ClientController {

    private final ClientJobLauncher launcher;
    private final ClientJobBuilder jobBuilder;
    private final BrokerMetadata brokerMetadata;

    @PostMapping("/run")
    public String runClientJob(@RequestBody Client config) {
        return "Job launched for client: " + config.getClientId();
    }

    @GetMapping
    public void willChangeToPostLater() {

        Client client3 = null;
        brokerMetadata.addOrUpdateClientConfig(client3);
        brokerMetadata.addOrUpdateClientJob(client3.getClientId(), jobBuilder.buildJob(client3));
        launcher.runJob(client3.getClientId());
    }

    @GetMapping(value = "/{clientId}")
    public void triggerJobByClientId(@PathVariable String clientId) {
        log.info("Running job for client ID : {}", clientId);
        launcher.runJob(clientId);
    }
}
