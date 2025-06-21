package org.aryak.batch.service;

import lombok.RequiredArgsConstructor;
import org.aryak.batch.model.Client;
import org.aryak.batch.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    
    public List<Client> fetchAll() {
        return clientRepository.findAll();
    }

    public void addClient(Client config) {
        clientRepository.save(config);
    }

    public void allClients(List<Client> configs) {
        clientRepository.saveAll(configs);
    }

    public Client getById(long id) {
        Optional<Client> opt = clientRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new RuntimeException("client with id :" + id + " does not exist!");
    }
}
