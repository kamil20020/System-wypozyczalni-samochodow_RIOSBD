package pl.edu.pwr.riosb.model.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarClientDTO {

    private Integer id;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private BigDecimal totalCost;
    private CarDTO car;
    private ClientDTO client;
}
