package pl.edu.pwr.riosb.model.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarClientDTO {

    private Integer id;
    private OffsetDateTime rentalDate;
    private OffsetDateTime returnDate;
    private BigDecimal totalCost;
    private CarDTO car;
    private ClientDTO client;
}
