package pl.edu.pwr.riosb.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.riosb.mapper.ClientMapper;
import pl.edu.pwr.riosb.model.api.dto.ClientDTO;
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

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAll(){

        List<ClientEntity> clientEntities = clientService.getAll();
        List<ClientDTO> clientDTOs = clientMapper.toDTO(clientEntities);

        return ResponseEntity.ok(clientDTOs);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ClientDTO gotClientDTO){

        if(gotClientDTO == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano wymaganych danych o kliencie");
        }

        ClientEntity gotClientEntity = clientMapper.fromDTO(gotClientDTO);

        ClientEntity createdClient = clientService.create(gotClientEntity);
        ClientDTO createdClientDTO = clientMapper.toDTO(createdClient);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdClientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable("id") String clientIdStr, @RequestBody ClientDTO gotClientDTO){

        Integer clientId;

        try{
            clientId = Integer.valueOf(clientIdStr);
        }
        catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Podano niewłaściwe id klienta");
        }

        if(gotClientDTO == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano wymaganych danych o kliencie");
        }

        ClientEntity gotClientEntity = clientMapper.fromDTO(gotClientDTO);

        try{
            clientService.updateById(clientId, gotClientEntity);
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String clientIdStr){

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

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
