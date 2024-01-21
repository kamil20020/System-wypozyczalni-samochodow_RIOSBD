package pl.edu.pwr.riosb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.riosb.exception.NotGivenException;
import pl.edu.pwr.riosb.mapper.CarMapper;
import pl.edu.pwr.riosb.model.api.dto.CarDTO;
import pl.edu.pwr.riosb.model.api.request.CreateCar;
import pl.edu.pwr.riosb.model.api.request.UpdateCar;
import pl.edu.pwr.riosb.model.entity.CarEntity;
import pl.edu.pwr.riosb.service.CarService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = "/cars")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper = CarMapper.INSTANCE;

    @Operation(summary = "Get all cars")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Got list of cars",
            content = { @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CarDTO.class))
            )}
        )
    })
    @GetMapping
    public ResponseEntity<List<CarDTO>> getAll(){

        List<CarEntity> carEntities = carService.getAll();
        List<CarDTO> carDTOs = carMapper.toDTO(carEntities);

        return ResponseEntity.ok(carDTOs);
    }

    @Operation(summary = "Get all available cars")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Got list of available cars",
            content = { @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CarDTO.class))
            )}
        )
    })
    @GetMapping("/available")
    public ResponseEntity<List<CarDTO>> getAllAvailable(){

        List<CarEntity> carEntities = carService.getAllAvailable();
        List<CarDTO> carDTOs = carMapper.toDTO(carEntities);

        return ResponseEntity.ok(carDTOs);
    }

    @Operation(summary = "Create car")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Car was created",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Not all car's data were given",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Car with given license number already exists",
            content = @Content
        ),
    })
    @PostMapping
    public ResponseEntity create(@RequestBody CreateCar createCar){

        if(createCar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano danych o samochodzie");
        }

        CarEntity gotCarEntity = carMapper.fromCreateRequest(createCar);
        CarEntity createdCar;

        try{
            createdCar = carService.create(gotCarEntity);
        }
        catch(NotGivenException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch(IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Utworzono samochód o id " + createdCar.getId());
    }

    @Operation(summary = "Replace car's data")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Car's data were updated",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Got invalid car id or car's data were not given",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Car with given id doesn't exist",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Car with given license number already exists",
            content = @Content
        ),
    })
    @PutMapping("/{id}")
    public ResponseEntity updateById(
        @Parameter(
            name = "id",
            description = "Id of the car which data should be replaced",
            required = true
        )
        @PathVariable("id") String carIdStr,
        @RequestBody UpdateCar updateCar
    ){
        Integer carId;

        try{
            carId = Integer.valueOf(carIdStr);
        }
        catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Podano niewłaściwe id samochodu");
        }

        if(updateCar == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano danych o samochodzie");
        }

        CarEntity gotCarEntity = carMapper.fromUpdateRequest(updateCar);

        try{
            carService.updateById(carId, gotCarEntity);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch(IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Zaktualizowano samochód o id " + carIdStr);
    }

    @Operation(summary = "Delete car by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Car was deleted",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid car id was given",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Car with given id doesn't exist",
            content = @Content
        ),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(
        @Parameter(
            name = "id",
            description = "Id of the car which should be deleted",
            required = true
        )
        @PathVariable("id") String carIdStr
    ){
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

        return ResponseEntity.status(HttpStatus.OK).body("Usunięto samochód o id " + carIdStr);
    }
}
