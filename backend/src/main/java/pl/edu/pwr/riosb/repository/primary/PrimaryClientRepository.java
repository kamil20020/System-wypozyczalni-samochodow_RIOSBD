package pl.edu.pwr.riosb.repository.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.riosb.model.entity.ClientEntity;

@Repository
public interface PrimaryClientRepository extends JpaRepository<ClientEntity, Integer> {
}
