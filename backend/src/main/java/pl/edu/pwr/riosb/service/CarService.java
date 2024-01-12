package pl.edu.pwr.riosb.service;

import jakarta.persistence.EntityNotFoundException;
import pl.edu.pwr.riosb.model.entity.CarEntity;

import java.util.List;

public interface CarService {

    List<CarEntity> getAll();
    List<CarEntity> getAllAvailable();
    CarEntity getById(Integer id);
    CarEntity create(CarEntity carEntity);
    void updateById(Integer id, CarEntity newCarData);
    void deleteById(Integer id);
}
