package pl.edu.pwr.riosb.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pwr.riosb.model.api.dto.CarDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarClientWithoutClient {

    private Integer id;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private BigDecimal totalCost;
    private CarDTO car;
}
