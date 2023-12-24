package pl.edu.pwr.riosb.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.edu.pwr.riosb.model.entity.ClientEntity;

import java.time.LocalDate;

public class ClientSpecification {

    public static Specification<ClientEntity> isNameEqualsIgnoreCase(String name){
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get("name")), name.toLowerCase());
        };
    }

    public static Specification<ClientEntity> isLastNameEqualsIgnoreCase(String lastName){
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get("lastName")), lastName.toLowerCase());
        };
    }

    public static Specification<ClientEntity> isEmailEquals(String email){
        return (root, query, builder) -> {
            return builder.equal(root.get("email"), email);
        };
    }

    public static Specification<ClientEntity> isPhoneNumberEquals(Integer phoneNumber){
        return (root, query, builder) -> {
            return builder.equal(root.get("phoneNumber"), phoneNumber);
        };
    }

    public static Specification<ClientEntity> isBirthDateEquals(LocalDate birthDate){
        return (root, query, builder) -> {
            return builder.equal(root.get("birthDate"), birthDate);
        };
    }
}
