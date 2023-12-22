package pl.edu.pwr.riosb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.edu.pwr.riosb.model.api.dto.CarDTO;
import pl.edu.pwr.riosb.model.api.request.CreateCar;
import pl.edu.pwr.riosb.model.api.request.UpdateCar;
import pl.edu.pwr.riosb.model.entity.CarEntity;

import java.util.List;

@Mapper(uses = Base64Mapper.class)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "photo", target = "photo", qualifiedByName = "toBase64")
    CarDTO toDTO(CarEntity carEntity);

    @Mapping(source = "photo", target = "photo", qualifiedByName = "fromBase64ToByteArray")
    CarEntity fromDTO(CarDTO carDTO);

    @Mapping(source = "photo", target = "photo", qualifiedByName = "fromBase64ToByteArray")
    CarEntity fromCreateRequest(CreateCar createCar);

    @Mapping(source = "photo", target = "photo", qualifiedByName = "fromBase64ToByteArray")
    CarEntity fromUpdateRequest(UpdateCar updateCar);

    List<CarDTO> toDTO(List<CarEntity> carEntities);
    List<CarEntity> fromDTO(List<CarDTO> carDTOs);
}
