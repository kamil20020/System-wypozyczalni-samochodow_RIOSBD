package pl.edu.pwr.riosb.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.riosb.model.entity.CarClientEntity;

import java.util.List;

@Repository
public interface SecondaryCarClientRepository extends JpaRepository<CarClientEntity, Integer> {

    List<CarClientEntity> findAllByClientEntity_ClientCode(Integer clientCode);
}
