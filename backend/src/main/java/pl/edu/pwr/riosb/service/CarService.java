package pl.edu.pwr.riosb.service;

import pl.edu.pwr.riosb.model.entity.CarEntity;

import java.util.List;

public interface CarService {

    List<CarEntity> getAll();
    CarEntity create(CarEntity carEntity);
    void updateById(Integer id, CarEntity newCarData);
    void deleteById(Integer id);
}
