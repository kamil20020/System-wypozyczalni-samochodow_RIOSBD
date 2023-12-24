package pl.edu.pwr.riosb.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.riosb.exception.NotGivenException;
import pl.edu.pwr.riosb.model.entity.CarClientEntity;
import pl.edu.pwr.riosb.model.entity.CarEntity;
import pl.edu.pwr.riosb.model.entity.ClientEntity;
import pl.edu.pwr.riosb.repository.primary.PrimaryCarClientRepository;
import pl.edu.pwr.riosb.repository.secondary.SecondaryCarClientRepository;
import pl.edu.pwr.riosb.service.CarClientService;
import pl.edu.pwr.riosb.service.CarService;
import pl.edu.pwr.riosb.service.ClientService;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarClientServiceImpl implements CarClientService {

    private final PrimaryCarClientRepository primaryCarClientRepository;
    private final SecondaryCarClientRepository secondaryCarClientRepository;
    private final CarService carService;
    private final ClientService clientService;

    @Override
    public List<CarClientEntity> getClientRentalsByClientCode(Integer clientCode) throws EntityNotFoundException {

        if(!clientService.existsByClientCode(clientCode)){
            throw new EntityNotFoundException("Nie istnieje klient o takim kodzie klienta");
        }

        return secondaryCarClientRepository.findAllByClientEntity_ClientCode(clientCode);
    }

    @Override
    public List<CarClientEntity> getAll() {
        return secondaryCarClientRepository.findAll();
    }

    private BigDecimal getTotalCost(BigDecimal carCostPer15Min, LocalDate rentalDate, LocalDate returnDate){

        long numberOfCosts = rentalDate.until(returnDate, DAYS) * 4 * 24;

        return BigDecimal.valueOf(carCostPer15Min.doubleValue() * numberOfCosts);
    }

    @Override
    public CarClientEntity create(Integer carId, Integer clientId, CarClientEntity carClientEntity)
        throws NotGivenException, EntityNotFoundException, IllegalArgumentException, IllegalStateException
    {
        if(carId == null){
            throw new NotGivenException("Nie podano id samochodu");
        }

        if(clientId == null){
            throw new NotGivenException("Nie podano id klienta");
        }

        ClientEntity clientEntity = clientService.getById(clientId);
        CarEntity carEntity = carService.getById(carId);

        if(carClientEntity.getRentalDate() == null){
            throw new NotGivenException("Nie podano daty początkowej wypożyczenia");
        }

        if(carClientEntity.getReturnDate() == null){
            throw new NotGivenException("Nie podano końcowej daty wypożyczenia");
        }

        if(carClientEntity.getRentalDate().isAfter(carClientEntity.getReturnDate())){
            throw new IllegalArgumentException("Początkowa data wypożyczenia musi być mniejsza lub równa końcowej dacie wypożyczenia");
        }

        if(secondaryCarClientRepository.existsByCarEntity_IdAndReturnDateIsGreaterThanEqual(
            carId, carClientEntity.getRentalDate()
        )){
            throw new IllegalStateException("Samochód już jest wypożyczony");
        }

        BigDecimal totalCost = getTotalCost(
            carEntity.getCost15min(), carClientEntity.getRentalDate(), carClientEntity.getReturnDate()
        );

        CarClientEntity newCarClientEntity = CarClientEntity.builder()
            .clientEntity(clientEntity)
            .carEntity(carEntity)
            .rentalDate(carClientEntity.getRentalDate())
            .returnDate(carClientEntity.getReturnDate())
            .totalCost(totalCost)
       .build();

        return primaryCarClientRepository.save(newCarClientEntity);
    }

    @Transactional
    @Override
    public void updateById(Integer id, Integer carId, Integer clientId, CarClientEntity carClientEntity)
        throws EntityNotFoundException, IllegalArgumentException
    {
        CarClientEntity foundCarClientEntity = primaryCarClientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie istnieje wypożyczenie o takim id"));

        if(carId != null){
            ClientEntity clientEntity = clientService.getById(clientId);
            foundCarClientEntity.setClientEntity(clientEntity);
        }

        if(clientId != null){
            CarEntity carEntity = carService.getById(carId);
            foundCarClientEntity.setCarEntity(carEntity);
        }

        if(carClientEntity.getRentalDate() != null){
            foundCarClientEntity.setRentalDate(carClientEntity.getRentalDate());
        }

        if(carClientEntity.getReturnDate() != null){
            foundCarClientEntity.setReturnDate(carClientEntity.getReturnDate());
        }

        if(carClientEntity.getRentalDate().isAfter(carClientEntity.getReturnDate())){
            throw new IllegalArgumentException("Początkowa data wypożyczenia musi być mniejsza lub równa końcowej dacie wypożyczenia");
        }

        BigDecimal totalCost = getTotalCost(
            foundCarClientEntity.getCarEntity().getCost15min(),
            carClientEntity.getRentalDate(),
            carClientEntity.getReturnDate()
        );

        carClientEntity.setTotalCost(totalCost);
    }

    @Override
    public void deleteById(Integer id) throws EntityNotFoundException{

        if(!secondaryCarClientRepository.existsById(id)){
            throw new EntityNotFoundException("Nie istnieje wypożyczenie o takim id");
        }

        primaryCarClientRepository.deleteById(id);
    }
}
