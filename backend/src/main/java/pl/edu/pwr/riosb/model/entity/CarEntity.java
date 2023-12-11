package pl.edu.pwr.riosb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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
    private String type;
    private String color;

    @Column(name = "licence_number")
    private String licenceNumber;

    @Column(name = "cost_15min")
    private BigDecimal cost15min;

    private byte[] photo;
}
