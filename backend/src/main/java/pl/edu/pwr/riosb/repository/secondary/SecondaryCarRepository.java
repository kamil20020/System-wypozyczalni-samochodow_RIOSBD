package pl.edu.pwr.riosb.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.riosb.model.entity.CarEntity;

import java.util.List;

@Repository
public interface SecondaryCarRepository extends JpaRepository<CarEntity, Integer> {

    @Query("""
        SELECT car
        FROM CarEntity car
        WHERE NOT EXISTS (
            SELECT 1
            FROM CarClientEntity car_client
            WHERE car_client.carEntity.id = car.id AND
                  CURRENT_DATE BETWEEN car_client.rentalDate AND car_client.returnDate
        )
    """
    )
    List<CarEntity> getAllAvailable();

    boolean existsByLicenceNumber(String licenseNumber);
}
