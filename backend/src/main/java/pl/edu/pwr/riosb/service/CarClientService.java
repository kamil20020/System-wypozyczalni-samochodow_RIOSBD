package pl.edu.pwr.riosb.service;

import pl.edu.pwr.riosb.model.entity.CarClientEntity;

import java.util.List;

public interface CarClientService {

    List<CarClientEntity> getClientRentalsByClientCode(Integer clientCode);
    List<CarClientEntity> getAll();
    CarClientEntity create(Integer carId, Integer clientId, CarClientEntity carClientEntity);
    void updateById(Integer id, Integer carId, Integer clientId, CarClientEntity carClientEntity);
    void deleteById(Integer id);
}