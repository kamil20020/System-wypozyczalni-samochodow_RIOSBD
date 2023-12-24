package pl.edu.pwr.riosb.service;

import pl.edu.pwr.riosb.model.entity.ClientEntity;

import java.util.List;

public interface ClientService {

    List<ClientEntity> getAll();
    ClientEntity getById(Integer id);
    boolean existsByClientCode(Integer clientCode);
    ClientEntity create(ClientEntity clientEntity);
    void updateById(Integer id, ClientEntity newClientData);
    void deleteById(Integer id);
}
