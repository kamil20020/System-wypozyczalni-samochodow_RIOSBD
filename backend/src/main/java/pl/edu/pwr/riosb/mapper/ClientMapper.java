package pl.edu.pwr.riosb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.edu.pwr.riosb.model.api.dto.CarDTO;
import pl.edu.pwr.riosb.model.api.dto.ClientDTO;
import pl.edu.pwr.riosb.model.entity.CarEntity;
import pl.edu.pwr.riosb.model.entity.ClientEntity;

import java.util.List;

@Mapper(uses = {DateMapper.class})
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "toOffsetDateTime")
    ClientDTO toDTO(ClientEntity clientEntity);

    @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "toLocalDate")
    ClientEntity fromDTO(ClientDTO clientDTO);

    List<ClientDTO> toDTO(List<ClientEntity> clientEntities);
}
