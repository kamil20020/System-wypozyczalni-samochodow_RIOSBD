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
import pl.edu.pwr.riosb.mapper.ClientMapper;
import pl.edu.pwr.riosb.model.api.dto.ClientDTO;
import pl.edu.pwr.riosb.model.api.request.CreateClient;
import pl.edu.pwr.riosb.model.api.request.UpdateClient;
import pl.edu.pwr.riosb.model.entity.ClientEntity;
import pl.edu.pwr.riosb.service.ClientService;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = "/clients")
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper = ClientMapper.INSTANCE;

    @Operation(summary = "Get all clients")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Got list of clients",
            content = { @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = ClientDTO.class))
            )}
        )
    })
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll(){

        List<ClientEntity> clientEntities = clientService.getAll();
        List<ClientDTO> clientDTOs = clientMapper.toDTO(clientEntities);

        return ResponseEntity.ok(clientDTOs);
    }

    @Operation(summary = "Create client")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Client was created",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Client's data were not given",
            content = @Content
        ),
    })
    @PostMapping
    public ResponseEntity create(@RequestBody CreateClient createClient){

        if(createClient == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano danych o kliencie");
        }

        ClientEntity gotClientEntity = clientMapper.fromCreateRequest(createClient);
        ClientEntity createdClient;

        try{
            createdClient = clientService.create(gotClientEntity);
        }
        catch(NotGivenException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Utworzono klienta o id " + createdClient.getId());
    }

    @Operation(summary = "Replace client's data")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Client's data were updated",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Goti invalid client id or client's data were not given",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Client with given id doesn't exist",
            content = @Content
        ),
    })
    @PutMapping("/{id}")
    public ResponseEntity updateById(
        @Parameter(
            name = "id",
            description = "Id of the client which data should be replaced",
            required = true
        )
        @PathVariable("id") String clientIdStr,
        @RequestBody UpdateClient updateClient
    ){
        Integer clientId;

        try{
            clientId = Integer.valueOf(clientIdStr);
        }
        catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Podano niewłaściwe id klienta");
        }

        if(updateClient == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano wymaganych danych o kliencie");
        }

        ClientEntity gotClientEntity = clientMapper.fromUpdateRequest(updateClient);

        try{
            clientService.updateById(clientId, gotClientEntity);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Zaktualizowano klienta o id " + clientIdStr);
    }

    @Operation(summary = "Delete client by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Client was removed",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid client id was given",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Client with given id doesn't exist",
            content = @Content
        ),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(
        @Parameter(
            name = "id",
            description = "Id of the client which should be removed",
            required = true
        )
        @PathVariable("id") String clientIdStr
    ){
        Integer clientId;

        try{
            clientId = Integer.valueOf(clientIdStr);
        }
        catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Podano niewłaściwe id klienta");
        }

        try{
            clientService.deleteById(clientId);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usunięto klienta o id " + clientIdStr);
    }
}
