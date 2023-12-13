package pl.edu.pwr.riosb.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.riosb.exception.NotGivenException;
import pl.edu.pwr.riosb.model.entity.CarEntity;
import pl.edu.pwr.riosb.repository.primary.PrimaryCarRepository;
import pl.edu.pwr.riosb.repository.secondary.SecondaryCarRepository;
import pl.edu.pwr.riosb.service.CarService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final PrimaryCarRepository primaryCarRepository;
    private final SecondaryCarRepository secondaryCarRepository;

    @Override
    public List<CarEntity> getAll() {
        return secondaryCarRepository.findAll();
    }

    @Override
    public CarEntity create(CarEntity carEntity) throws NotGivenException {
        return primaryCarRepository.save(carEntity);
    }

    @Transactional
    @Override
    public void updateById(Integer id, CarEntity newCarData) throws EntityNotFoundException{

        CarEntity foundCar = primaryCarRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie istnieje samochod o takim id"));

        log.info(newCarData.toString());

        foundCar.setColor(newCarData.getColor());
        foundCar.setManufacturer(newCarData.getManufacturer());
        foundCar.setType(newCarData.getType());
        foundCar.setLicenceNumber(newCarData.getLicenceNumber());
        foundCar.setCost15min(newCarData.getCost15min());
        foundCar.setPhoto(newCarData.getPhoto());

        log.info(foundCar.toString());
    }

    @Override
    public void deleteById(Integer id) throws EntityNotFoundException{

        if(!secondaryCarRepository.existsById(id)){
            throw new EntityNotFoundException("Nie istnieje samochod o takim id");
        }

        primaryCarRepository.deleteById(id);
    }
}
