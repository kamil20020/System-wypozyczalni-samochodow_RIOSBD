package pl.edu.pwr.riosb.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import pl.edu.pwr.riosb.model.entity.CarClientEntity;
import pl.edu.pwr.riosb.model.entity.CarClientEntity_;
import pl.edu.pwr.riosb.model.entity.CarEntity_;

import java.time.LocalDate;

public class CarClientSpecification {

    public static Specification<CarClientEntity> carIdEquals(Integer carId){
        return (root, query, builder) -> {
            return builder.equal(root.get(CarClientEntity_.carEntity).get(CarEntity_.id), carId);
        };
    }

    public static Specification<CarClientEntity> rentalRangeOverlaps(LocalDate rentalStart, LocalDate rentalEnd){
        return (root, query, builder) -> {
            return builder.and(
                builder.lessThan(root.get(CarClientEntity_.rentalDate), rentalEnd),
                builder.greaterThan(root.get(CarClientEntity_.returnDate), rentalStart)
            );
        };
    }
}
