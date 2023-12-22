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
    public CarEntity create(CarEntity carEntity) throws NotGivenException, IllegalStateException {

        if(carEntity.getManufacturer() == null){
            throw new NotGivenException("Nie podano producenta");
        }

        if(carEntity.getType() == null){
            throw new NotGivenException("Nie podano typu");
        }

        if(carEntity.getModel() == null){
            throw new NotGivenException("Nie podano modelu");
        }

        if(carEntity.getColor() == null){
            throw new NotGivenException("Nie podano koloru");
        }

        if(carEntity.getLicenceNumber() == null){
            throw new NotGivenException("Nie podano numeru rejestracyjnego");
        }

        if(carEntity.getCost15min() == null){
            throw new NotGivenException("Nie podano kosztu wynajmu na 15 minut");
        }

        if(carEntity.getPhoto() == null){
            throw new NotGivenException("Nie podano zdjęcia");
        }

        if(secondaryCarRepository.existsByLicenceNumber(carEntity.getLicenceNumber())){
            throw new IllegalStateException("Istnieje już samochód z takim numerem rejestracyjnym");
        }

        return primaryCarRepository.save(carEntity);
    }

    @Transactional
    @Override
    public void updateById(Integer id, CarEntity newCarData) throws EntityNotFoundException, IllegalStateException{

        CarEntity foundCar = primaryCarRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie istnieje samochod o takim id"));

        if(newCarData.getColor() != null){
            foundCar.setColor(newCarData.getColor());
        }

        if(newCarData.getManufacturer() != null){
            foundCar.setManufacturer(newCarData.getManufacturer());
        }

        if(newCarData.getType() != null){
            foundCar.setType(newCarData.getType());
        }

        if(newCarData.getModel() != null){
            foundCar.setModel(newCarData.getModel());
        }

        if(newCarData.getLicenceNumber() != null){

            if(secondaryCarRepository.existsByLicenceNumber(newCarData.getLicenceNumber())){
                throw new IllegalStateException("Istnieje już samochód z takim numerem rejestracyjnym");
            }

            foundCar.setLicenceNumber(newCarData.getLicenceNumber());
        }

        if(newCarData.getCost15min() != null){
            foundCar.setCost15min(newCarData.getCost15min());
        }

        if(newCarData.getPhoto() != null){
            foundCar.setPhoto(newCarData.getPhoto());
        }
    }

    @Override
    public void deleteById(Integer id) throws EntityNotFoundException{

        if(!secondaryCarRepository.existsById(id)){
            throw new EntityNotFoundException("Nie istnieje samochod o takim id");
        }

        primaryCarRepository.deleteById(id);
    }
}
