package pl.edu.pwr.riosb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.*;

@Mapper
public interface DateMapper {

    DateMapper INSTANCE = Mappers.getMapper(DateMapper.class);

    @Named("toLocalDate")
    default LocalDate toLocalDate(OffsetDateTime offsetDateTime){
        if(offsetDateTime == null){
            return null;
        }

        return offsetDateTime.toLocalDate();
    }

    @Named("toOffsetDateTime")
    default OffsetDateTime toOffsetDateTime(LocalDate localDate){
        if(localDate == null){
            return null;
        }

        LocalTime nowTime = LocalTime.now();
        ZoneOffset zone = OffsetDateTime.now().getOffset();

        return OffsetDateTime.of(localDate, nowTime, zone);
    }
}
