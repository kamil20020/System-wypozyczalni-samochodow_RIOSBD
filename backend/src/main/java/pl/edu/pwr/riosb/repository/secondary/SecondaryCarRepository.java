package pl.edu.pwr.riosb.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.riosb.model.entity.CarEntity;

@Repository
public interface SecondaryCarRepository extends JpaRepository<CarEntity, Integer> {

    boolean existsByLicenceNumber(String licenseNumber);
}
