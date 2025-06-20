package org.aryak.batch.service;

import lombok.RequiredArgsConstructor;
import org.aryak.batch.model.Client;
import org.aryak.batch.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(Client config) {
        return clientRepository.save(config);
    }

    public List<Client> fetchAll() {
        return clientRepository.findAll();
    }
}
