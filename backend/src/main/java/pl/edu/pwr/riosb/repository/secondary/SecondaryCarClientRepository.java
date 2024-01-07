package pl.edu.pwr.riosb.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.riosb.model.entity.CarClientEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SecondaryCarClientRepository extends JpaRepository<CarClientEntity, Integer>, JpaSpecificationExecutor<CarClientEntity> {

    List<CarClientEntity> findAllByClientEntity_ClientCode(Integer clientCode);
    boolean existsByCarEntity_IdAndReturnDateIsGreaterThanEqual(Integer carId, LocalDate actualDate);
}
