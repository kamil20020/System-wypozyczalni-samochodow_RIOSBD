package pl.edu.pwr.riosb.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarClient {

    private OffsetDateTime rentalDate;
    private OffsetDateTime returnDate;
    private Integer clientId;
    private Integer carId;
}
