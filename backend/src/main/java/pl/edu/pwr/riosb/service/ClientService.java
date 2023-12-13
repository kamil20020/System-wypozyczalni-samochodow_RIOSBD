package pl.edu.pwr.riosb.service;

import pl.edu.pwr.riosb.model.entity.ClientEntity;

import java.util.List;

public interface ClientService {

    List<ClientEntity> getAll();
    ClientEntity create(ClientEntity clientEntity);
    void updateById(Integer id, ClientEntity newCarData);
    void deleteById(Integer id);
}
