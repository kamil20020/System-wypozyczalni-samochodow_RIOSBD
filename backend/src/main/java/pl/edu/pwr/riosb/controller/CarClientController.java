package pl.edu.pwr.riosb.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import pl.edu.pwr.riosb.mapper.CarClientMapper;
import pl.edu.pwr.riosb.model.api.dto.CarClientDTO;
import pl.edu.pwr.riosb.model.api.dto.CarDTO;
import pl.edu.pwr.riosb.model.api.response.CarClientWithoutClient;
import pl.edu.pwr.riosb.model.entity.CarClientEntity;
import pl.edu.pwr.riosb.service.CarClientService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/cars-clients")
public class CarClientController {

    private final CarClientService carClientService;
    private CarClientMapper carClientMapper = CarClientMapper.INSTANCE;

    @Operation(summary = "Get client's rentals list by given client code")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "List of client's rentals",
            content = { @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CarClientWithoutClient.class))
            )}
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Got invalid client code",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Client with given client code doesn't exist",
            content = @Content
        ),
    })
    @GetMapping("/client-code/{clientCode}")
    public ResponseEntity getClientRentalsByClientCode(
        @PathVariable("clientCode") String clientCodeStr
    ){
        Integer clientCode;

        try{
            clientCode = Integer.valueOf(clientCodeStr);
        }
        catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Podano niewłaściwy kod klienta");
        }

        List<CarClientEntity> clientRentals;

        try{
            clientRentals = carClientService.getClientRentalsByClientCode(clientCode);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        List<CarClientWithoutClient> formattedClientRentals = carClientMapper.toWithoutClient(clientRentals);

        return ResponseEntity.ok(formattedClientRentals);
    }

    @Operation(summary = "Get all rentals")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Got list of rentals",
            content = { @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = CarClientDTO.class))
            )}
        )
    })
    @GetMapping
    public ResponseEntity<List<CarClientDTO>> getAll(){

        List<CarClientEntity> foundCarClientEntities = carClientService.getAll();
        List<CarClientDTO> foundCarClientDTOs = carClientMapper.toDTO(foundCarClientEntities);

        return ResponseEntity.ok(foundCarClientDTOs);
    }
}
