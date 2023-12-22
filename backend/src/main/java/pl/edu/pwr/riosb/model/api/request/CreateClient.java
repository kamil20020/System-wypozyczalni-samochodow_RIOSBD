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
public class CreateClient {

    private String name;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private OffsetDateTime birthDate;
}
