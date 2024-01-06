package pl.edu.pwr.riosb.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.riosb.exception.NotGivenException;
import pl.edu.pwr.riosb.model.entity.ClientEntity;
import pl.edu.pwr.riosb.repository.primary.PrimaryClientRepository;
import pl.edu.pwr.riosb.repository.secondary.SecondaryClientRepository;
import pl.edu.pwr.riosb.service.ClientService;

import java.util.List;
import java.util.Random;

import static org.springframework.data.jpa.domain.Specification.where;
import static pl.edu.pwr.riosb.specification.ClientSpecification.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final PrimaryClientRepository primaryClientRepository;
    private final SecondaryClientRepository secondaryClientRepository;

    @Override
    public List<ClientEntity> getAll() {
        return secondaryClientRepository.findAll();
    }

    @Override
    public ClientEntity getById(Integer id) throws EntityNotFoundException {

        return secondaryClientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie istnieje klient o takim id"));
    }

    @Override
    public boolean existsByClientCode(Integer clientCode) {
        return secondaryClientRepository.existsByClientCode(clientCode);
    }

    @Override
    public ClientEntity create(ClientEntity clientEntity) throws NotGivenException {

        if(clientEntity.getName() == null){
            throw new NotGivenException("Nie podano imienia");
        }

        if(clientEntity.getLastName() == null){
            throw new NotGivenException("Nie podano nazwiska");
        }

        if(clientEntity.getPhoneNumber() == null){
            throw new NotGivenException("Nie podano numeru telefonu");
        }

        if(clientEntity.getBirthDate() == null){
            throw new NotGivenException("Nie podano dany urodzenia");
        }

        if(clientEntity.getEmail() == null){
            throw new NotGivenException("Nie podano adresu e-mail");
        }

        return secondaryClientRepository.findOne(
            where(isBirthDateEquals(clientEntity.getBirthDate())
            .and(isPhoneNumberEquals(clientEntity.getPhoneNumber()))
            .and(isEmailEquals(clientEntity.getEmail())))
            .and(isNameEqualsIgnoreCase(clientEntity.getName()))
            .and(isLastNameEqualsIgnoreCase(clientEntity.getLastName()))
        )
        .orElseGet(() -> {
            clientEntity.setClientCode(new Random().nextInt(0, Integer.MAX_VALUE));
            return primaryClientRepository.save(clientEntity);
        });
    }

    @Transactional
    @Override
    public void updateById(Integer id, ClientEntity newClientData) throws EntityNotFoundException {

        ClientEntity foundClient = primaryClientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie istnieje klient o takim id"));

        if(newClientData.getName() != null){
            foundClient.setName(newClientData.getName());
        }

        if(newClientData.getLastName() != null){
            foundClient.setLastName(newClientData.getLastName());
        }

        if(newClientData.getEmail() != null){
            foundClient.setEmail(newClientData.getEmail());
        }

        if(newClientData.getPhoneNumber() != null){
            foundClient.setPhoneNumber(newClientData.getPhoneNumber());
        }

        if(newClientData.getBirthDate() != null){
            foundClient.setBirthDate(newClientData.getBirthDate());
        }
    }

    @Override
    public void deleteById(Integer id) throws EntityNotFoundException{

        if(!secondaryClientRepository.existsById(id)){
            throw new EntityNotFoundException("Nie istnieje klient o takim id");
        }

        primaryClientRepository.deleteById(id);
    }
}
