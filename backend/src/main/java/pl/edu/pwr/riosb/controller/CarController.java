package pl.edu.pwr.riosb.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.riosb.mapper.CarMapper;
import pl.edu.pwr.riosb.model.api.dto.CarDTO;
import pl.edu.pwr.riosb.model.entity.CarEntity;
import pl.edu.pwr.riosb.repository.primary.PrimaryCarRepository;
import pl.edu.pwr.riosb.repository.secondary.SecondaryCarRepository;
import pl.edu.pwr.riosb.service.CarService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = "/cars")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper = CarMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAll(){

        List<CarEntity> carEntities = carService.getAll();
        List<CarDTO> carDTOs = carMapper.toDTO(carEntities);

        return ResponseEntity.ok(carDTOs);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CarDTO gotCarDTO){

        if(gotCarDTO == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano wymaganych danych o samochodzie");
        }

        CarEntity gotCarEntity = carMapper.fromDTO(gotCarDTO);

        CarEntity createdCar = carService.create(gotCarEntity);
        CarDTO createdCarDTO = carMapper.toDTO(createdCar);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable("id") String carIdStr, @RequestBody CarDTO gotCarDTO){

        Integer carId;

        try{
            carId = Integer.valueOf(carIdStr);
        }
        catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Podano niewłaściwe id samochodu");
        }

        if(gotCarDTO == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano wymaganych danych o samochodzie");
        }

        CarEntity gotCarEntity = carMapper.fromDTO(gotCarDTO);

        try{
            carService.updateById(carId, gotCarEntity);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String carIdStr){

        Integer carId;

        try{
            carId = Integer.valueOf(carIdStr);
        }
        catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Podano niewłaściwe id samochodu");
        }

        try{
            carService.deleteById(carId);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
