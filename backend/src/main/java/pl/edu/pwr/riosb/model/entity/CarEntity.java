package pl.edu.pwr.riosb.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CARS")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String manufacturer;
    private String model;
    private String type;
    private String color;

    @Column(name = "licence_number")
    private String licenceNumber;

    @Column(name = "cost_15min")
    private BigDecimal cost15min;

    private byte[] photo;

    private String location;

    @JsonIgnore
    @OneToMany(mappedBy = "carEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CarClientEntity> carClientEntities;
}
