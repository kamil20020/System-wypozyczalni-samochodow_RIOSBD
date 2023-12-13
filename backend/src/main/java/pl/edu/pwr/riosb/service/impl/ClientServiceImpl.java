package pl.edu.pwr.riosb.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.riosb.model.entity.ClientEntity;
import pl.edu.pwr.riosb.repository.primary.PrimaryClientRepository;
import pl.edu.pwr.riosb.repository.secondary.SecondaryClientRepository;
import pl.edu.pwr.riosb.service.ClientService;

import java.util.List;

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
    public ClientEntity create(ClientEntity clientEntity) {
        return primaryClientRepository.save(clientEntity);
    }

    @Transactional
    @Override
    public void updateById(Integer id, ClientEntity newCarData) throws EntityNotFoundException {

        ClientEntity foundClient = primaryClientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Nie istnieje klient o takim id"));

        foundClient.setName(newCarData.getName());
        foundClient.setLastName(newCarData.getLastName());
        foundClient.setEmail(newCarData.getEmail());
        foundClient.setPhoneNumber(newCarData.getPhoneNumber());
        foundClient.setBirthDate(newCarData.getBirthDate());
        foundClient.setClientCode(newCarData.getClientCode());
    }

    @Override
    public void deleteById(Integer id) throws EntityNotFoundException{

        if(!secondaryClientRepository.existsById(id)){
            throw new EntityNotFoundException("Nie istnieje klient o takim id");
        }

        primaryClientRepository.deleteById(id);
    }
}
