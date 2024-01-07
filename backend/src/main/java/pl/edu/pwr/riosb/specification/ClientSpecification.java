package pl.edu.pwr.riosb.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.edu.pwr.riosb.model.entity.ClientEntity;
import pl.edu.pwr.riosb.model.entity.ClientEntity_;

import java.time.LocalDate;

public class ClientSpecification {

    public static Specification<ClientEntity> isNameEqualsIgnoreCase(String name){
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get(ClientEntity_.name)), name.toLowerCase());
        };
    }

    public static Specification<ClientEntity> isLastNameEqualsIgnoreCase(String lastName){
        return (root, query, builder) -> {
            return builder.equal(builder.lower(root.get(ClientEntity_.lastName)), lastName.toLowerCase());
        };
    }

    public static Specification<ClientEntity> isEmailEquals(String email){
        return (root, query, builder) -> {
            return builder.equal(root.get(ClientEntity_.email), email);
        };
    }

    public static Specification<ClientEntity> isPhoneNumberEquals(Integer phoneNumber){
        return (root, query, builder) -> {
            return builder.equal(root.get(ClientEntity_.phoneNumber), phoneNumber);
        };
    }

    public static Specification<ClientEntity> isBirthDateEquals(LocalDate birthDate){
        return (root, query, builder) -> {
            return builder.equal(root.get(ClientEntity_.birthDate), birthDate);
        };
    }
}
