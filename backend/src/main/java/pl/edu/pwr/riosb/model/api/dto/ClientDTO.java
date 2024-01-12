package pl.edu.pwr.riosb.model.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private LocalDate birthDate;
    private Integer clientCode;
}
