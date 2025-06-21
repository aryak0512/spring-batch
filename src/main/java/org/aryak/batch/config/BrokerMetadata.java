package org.aryak.batch.config;

import lombok.Getter;
import org.aryak.batch.model.Client;
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

    private final Map<Long, Job> clientJobs = new ConcurrentHashMap<>();
    private final Map<Long, Client> clientConfigs = new ConcurrentHashMap<>();

    public Job getClientJob(Long clientId) {

        if (clientJobs.containsKey(clientId)) {
            return clientJobs.get(clientId);
        }

        throw new RuntimeException("Yet to be implemented");
    }

    public Client getClientConfig(Long clientId) {

        if (clientConfigs.containsKey(clientId)) {
            return clientConfigs.get(clientId);
        }
        throw new RuntimeException("Yet to be implemented");
    }

    public void addOrUpdateClientJob(Long clientId, Job job) {
        clientJobs.put(clientId, job);
    }

    public void addOrUpdateClientConfig(Client client) {
        clientConfigs.put(client.getId(), client);
    }
}
