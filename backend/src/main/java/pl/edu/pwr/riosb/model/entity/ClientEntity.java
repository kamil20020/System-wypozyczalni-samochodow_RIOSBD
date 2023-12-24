package pl.edu.pwr.riosb.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENTS")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "client_code")
    private Integer clientCode;

    @JsonIgnore
    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CarClientEntity> carClientEntities;
}
