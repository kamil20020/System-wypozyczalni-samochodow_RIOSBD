package pl.edu.pwr.riosb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.edu.pwr.riosb.model.api.dto.CarClientDTO;
import pl.edu.pwr.riosb.model.api.request.CreateCarClient;
import pl.edu.pwr.riosb.model.api.request.UpdateCarClient;
import pl.edu.pwr.riosb.model.api.response.CarClientWithoutClient;
import pl.edu.pwr.riosb.model.entity.CarClientEntity;

import java.util.List;

@Mapper(uses = {CarMapper.class, ClientMapper.class})
public interface CarClientMapper {

    CarClientMapper INSTANCE = Mappers.getMapper(CarClientMapper.class);

    CarClientEntity fromCreateRequest(CreateCarClient createCarClient);

    CarClientEntity fromUpdateRequest(UpdateCarClient updateCarClient);

    @Mapping(source = "carEntity", target = "car")
    @Mapping(source = "clientEntity", target = "client")
    CarClientDTO toDTO(CarClientEntity clientEntity);

    List<CarClientDTO> toDTO(List<CarClientEntity> carClientEntities);

    @Mapping(source = "carEntity", target = "car")
    CarClientWithoutClient toWithoutClient(CarClientEntity carClientEntity);

    List<CarClientWithoutClient> toWithoutClient(List<CarClientEntity> carClientEntities);
}
