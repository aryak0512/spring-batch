package org.aryak.batch.v2.config;

import lombok.Getter;
import org.aryak.batch.model.ClientConfig;
import org.springframework.batch.core.Job;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean that maintains all clients' metadata and job definitions
 */
@Getter
@Component
public class BrokerMetadata {

    private final Map<String, Job> clientJobs = new ConcurrentHashMap<>();
    private final Map<String, ClientConfig> clientConfigs = new ConcurrentHashMap<>();

    public Job getClientJob(String clientId) {

        if (clientJobs.containsKey(clientId)) {
            return clientJobs.get(clientId);
        }

        throw new RuntimeException("Yet to be implemented");
    }

    public ClientConfig getClientConfig(String clientId) {

        if (clientConfigs.containsKey(clientId)) {
            return clientConfigs.get(clientId);
        }
        throw new RuntimeException("Yet to be implemented");
    }

    public void addOrUpdateClientJob(String clientId, Job job) {
        clientJobs.put(clientId, job);
    }

    public void addOrUpdateClientConfig(ClientConfig clientConfig) {
        clientConfigs.put(clientConfig.clientId(), clientConfig);
    }
}
