package org.aryak.batch.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aryak.batch.config.ClientMetadata;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientInfoThread {

    private final ClientMetadata clientMetadata;

    @Scheduled(fixedDelay = 10000)
    void getInfo() {
        System.out.println("Client config map : " + clientMetadata.getClientConfigs());
        System.out.println("Client mapping map : " + clientMetadata.getClientMappings());
        System.out.println("===============================");
    }
}
