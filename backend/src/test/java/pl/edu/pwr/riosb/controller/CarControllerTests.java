package pl.edu.pwr.riosb.controller;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pwr.riosb.mapper.CarMapper;
import pl.edu.pwr.riosb.model.api.dto.CarDTO;
import pl.edu.pwr.riosb.model.entity.CarEntity;
import pl.edu.pwr.riosb.repository.primary.PrimaryCarRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CarControllerTests {

    @Autowired
    private PrimaryCarRepository primaryCarRepository;

    private CarMapper carMapper = CarMapper.INSTANCE;

    private final String url = "http://localhost:9000/cars";

    @BeforeEach
    public void load(){

        List<CarEntity> carEntities = new ArrayList<>();

        for(int i=1; i <= 10; i++){
            carEntities.add(
                CarEntity.builder()
                    .id(i)
                    .color("Color " + i)
                    .cost15min(new BigDecimal(i * 10))
                    .licenceNumber("Licence " + i)
                    .manufacturer("Manufacturer " + i)
                    .type("Type " + i)
                .build()
            );
        }

        primaryCarRepository.saveAll(carEntities);
    }

    @Test
    public void shouldGetAll(){

        Response response = when().get(url);

        response.then().statusCode(200);

        List<CarDTO> carDTOs = Arrays.stream(response.as(CarDTO[].class))
            .toList();

        List<CarEntity> carEntities = carMapper.fromDTO(carDTOs);

        assertEquals(carEntities.size(), 10);
    }
}
