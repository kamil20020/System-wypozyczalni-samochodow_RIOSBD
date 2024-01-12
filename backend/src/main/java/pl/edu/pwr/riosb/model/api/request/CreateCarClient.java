package pl.edu.pwr.riosb.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarClient {

    private LocalDate rentalDate;
    private LocalDate returnDate;
    private CreateClient createClient;
    private Integer carId;
}
