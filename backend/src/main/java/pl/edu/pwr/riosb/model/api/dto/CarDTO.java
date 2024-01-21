package pl.edu.pwr.riosb.model.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private Integer id;
    private String manufacturer;
    private String model;
    private String type;
    private String color;
    private String licenceNumber;
    private BigDecimal cost15min;
    private String photo;
    private String location;
}
