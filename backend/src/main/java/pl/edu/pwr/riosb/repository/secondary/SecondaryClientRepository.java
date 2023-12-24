package pl.edu.pwr.riosb.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.riosb.model.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecondaryClientRepository extends JpaRepository<ClientEntity, Integer>, JpaSpecificationExecutor<ClientEntity> {

    boolean existsByClientCode(Integer clientCode);
}
