package pl.edu.pwr.riosb.service.impl;

import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public CarClientEntity create(Integer carId, Integer clientId, CarClientEntity carClientEntity)
        throws EntityNotFoundException, IllegalArgumentException, NotGivenException
    {
        ClientEntity clientEntity = clientService.getById(carId);
        CarEntity carEntity = carService.getById(clientId);

        if(carClientEntity.getRentalDate() == null){
            throw new NotGivenException("Nie podano daty początkowej wypożyczenia");
        }

        if(carClientEntity.getReturnDate() == null){
            throw new NotGivenException("Nie podano końcowej daty wypożyczenia");
        }

        if(carClientEntity.getRentalDate().isAfter(carClientEntity.getReturnDate())){
            throw new IllegalArgumentException("Początkowa data wypożyczenia musi być mniejsza lub równa końcowej dacie wypożyczenia");
        }

        BigDecimal carCostPer15Min = carEntity.getCost15min();
        LocalDate rentalDate = carClientEntity.getRentalDate();
        LocalDate returnDate = carClientEntity.getReturnDate();
        long numberOfCosts = rentalDate.until(returnDate, ChronoUnit.HOURS) * 4;
        BigDecimal totalCost = BigDecimal.valueOf(carCostPer15Min.doubleValue() * numberOfCosts);

        CarClientEntity newCarClientEntity = CarClientEntity.builder()
            .clientEntity(clientEntity)
            .carEntity(carEntity)
            .rentalDate(carClientEntity.getRentalDate())
            .returnDate(carClientEntity.getReturnDate())
            .totalCost(totalCost)
       .build();

        return primaryCarClientRepository.save(newCarClientEntity);
    }

    @Override
    public void updateById(Integer id, CarClientEntity carClientEntity) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
