package pl.edu.pwr.riosb;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
//@Entity
//@Table(name = "Test")
@NoArgsConstructor
@AllArgsConstructor
public class TestEntity {

    @Id
    private Integer id;
    private String name;
}
