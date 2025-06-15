package org.aryak.batch.v2;

import lombok.RequiredArgsConstructor;
import org.aryak.batch.model.ClientConfig;
import org.aryak.batch.v2.config.BrokerMetadata;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientJobLauncher launcher;
    private final ClientJobBuilder jobBuilder;
    private final BrokerMetadata brokerMetadata;


    @PostMapping("/run")
    public String runClientJob(@RequestBody ClientConfig config) {

        return "Job launched for client: " + config.clientId();
    }

    @GetMapping
    public void willChangeToPostLater() {
        var client3 = new ClientConfig(
                "c3",
                "/Users/aryak/Desktop/test.csv",
                ":",
                List.of("id", "first_name"),
                0,
                3);
        brokerMetadata.addOrUpdateClientConfig(client3);
        brokerMetadata.addOrUpdateClientJob(client3.clientId(), jobBuilder.buildJob(client3));

        launcher.runJob(client3.clientId());
    }
}
