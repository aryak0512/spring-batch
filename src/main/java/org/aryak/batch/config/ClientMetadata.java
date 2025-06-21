package org.aryak.batch.config;

import lombok.Getter;
import org.aryak.batch.exceptions.NoSuchClientException;
import org.aryak.batch.model.Client;
import org.aryak.batch.model.CsvMapping;
import org.springframework.batch.core.Job;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean that maintains all clients' metadata and job definitions.
 * The members of this metadata must not be exposed to other threads.
 * <p>
 * Mutation must only be permitted via <code>addOrUpdateClientJob</code>
 * and <code>addOrUpdateClientConfig</code> methods
 */
@Getter
@Component
public class ClientMetadata {

    /* holds client and its job instances */
    private final Map<Long, Job> clientJobs = new ConcurrentHashMap<>();

    /* holds client and its reader config */
    private final Map<Long, Client> clientConfigs = new ConcurrentHashMap<>();

    /* holds client and its mapping config */
    private final Map<Long, CsvMapping> clientMappings = new ConcurrentHashMap<>();

    private <K, V> V get(K key, Map<K, V> map) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        throw new NoSuchClientException((Long) key);
    }

    private <K, V> void put(K key, V value, Map<K, V> map) {
        map.put(key, value);
    }

    // methods accessible to outer world begin

    public Job getClientJob(Long clientId) {
        return get(clientId, clientJobs);
    }

    public Client getClientConfig(Long clientId) {
        return get(clientId, clientConfigs);
    }

    public CsvMapping getClientMapping(Long clientId) {
        return get(clientId, clientMappings);
    }

    public void addOrUpdateClientJob(Long clientId, Job job) {
        put(clientId, job, clientJobs);
    }

    public void addOrUpdateClientConfig(long clientId, Client client) {
        put(clientId, client, clientConfigs);
    }

    public void addOrUpdateClientMapping(long clientId, CsvMapping mapping) {
        put(clientId, mapping, clientMappings);
    }
}
