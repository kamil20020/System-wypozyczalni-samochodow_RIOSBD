package pl.edu.pwr.riosb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.riosb.model.entity.CarEntity;
import pl.edu.pwr.riosb.repository.primary.PrimaryCarRepository;
import pl.edu.pwr.riosb.repository.secondary.SecondaryCarRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(path = "/cars")
public class CarController {

    private final PrimaryCarRepository primaryCarRepository;
    private final SecondaryCarRepository secondaryCarRepository;

    @GetMapping("/primary")
    public ResponseEntity<List<CarEntity>> getCarsPrimary(){
        return ResponseEntity.ok(primaryCarRepository.findAll());
    }

    @GetMapping("/secondary")
    public ResponseEntity<List<CarEntity>> getCarsSecondary(){
        return ResponseEntity.ok(secondaryCarRepository.findAll());
    }
}
