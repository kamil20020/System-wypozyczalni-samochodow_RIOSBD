package pl.edu.pwr.riosb.service;

import pl.edu.pwr.riosb.model.entity.CarClientEntity;
import pl.edu.pwr.riosb.model.entity.ClientEntity;

import java.util.List;

public interface CarClientService {

    List<CarClientEntity> getClientRentalsByClientCode(Integer clientCode);
    List<CarClientEntity> getAll();
    CarClientEntity create(Integer carId, ClientEntity clientEntity, CarClientEntity carClientEntity);
    void updateById(Integer id, Integer carId, Integer clientId, CarClientEntity carClientEntity);
    void deleteById(Integer id);
}