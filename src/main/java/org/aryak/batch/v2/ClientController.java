package org.aryak.batch.v2;

import org.aryak.batch.model.ClientConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientJobLauncher launcher;

    public ClientController(ClientJobLauncher launcher) {
        this.launcher = launcher;
    }

    @PostMapping("/run")
    public String runClientJob(@RequestBody ClientConfig config) {

        return "Job launched for client: " + config.clientId();
    }
}
