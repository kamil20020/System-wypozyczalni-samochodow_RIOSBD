package pl.edu.pwr.riosb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.edu.pwr.riosb.model.api.dto.ClientDTO;
import pl.edu.pwr.riosb.model.api.request.CreateClient;
import pl.edu.pwr.riosb.model.api.request.UpdateClient;
import pl.edu.pwr.riosb.model.entity.ClientEntity;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO toDTO(ClientEntity clientEntity);

    ClientEntity fromCreateRequest(CreateClient createClient);

    ClientEntity fromUpdateRequest(UpdateClient updateClient);

    List<ClientDTO> toDTO(List<ClientEntity> clientEntities);
}
