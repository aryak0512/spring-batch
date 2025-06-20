package org.aryak.batch.controller;

import lombok.RequiredArgsConstructor;
import org.aryak.batch.config.BrokerMetadata;
import org.aryak.batch.config.job.ClientJobBuilder;
import org.aryak.batch.config.job.ClientJobLauncher;
import org.aryak.batch.model.ClientConfig;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientJobLauncher launcher;
    private final ClientJobBuilder jobBuilder;
    private final BrokerMetadata brokerMetadata;

    @PostMapping("/run")
    public String runClientJob(@RequestBody ClientConfig config) {
        return "Job launched for client: " + config.getClientId();
    }

    @GetMapping
    public void willChangeToPostLater() {

        ClientConfig client3 = null;
        brokerMetadata.addOrUpdateClientConfig(client3);
        brokerMetadata.addOrUpdateClientJob(client3.getClientId(), jobBuilder.buildJob(client3));
        launcher.runJob(client3.getClientId());
    }
}
