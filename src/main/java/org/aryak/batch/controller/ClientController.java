package org.aryak.batch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.config.job.ClientJobLauncher;
import org.aryak.batch.model.Client;
import org.aryak.batch.service.ClientService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/client")
public class ClientController {

    private final ClientJobLauncher launcher;
    private final ClientService clientService;

    @PostMapping(value = "/save")
    public void addClient(@RequestBody Client config) {
        clientService.addClient(config);
    }

    @GetMapping(value = "/run/{clientId}")
    public void triggerJobByClientId(@PathVariable Long clientId) {
        log.info("Running job for client ID : {}", clientId);
        launcher.runJob(clientId);
    }

    @GetMapping(value = "/{id}")
    public Client triggerJobByClientId(@PathVariable long id) {
        return clientService.getById(id);
    }
}
