package pl.edu.pwr.riosb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Base64;

@Mapper
public interface Base64Mapper {

    Base64Mapper INSTANCE = Mappers.getMapper(Base64Mapper.class);

    @Named("fromBase64ToByteArray")
    default byte[] fromBase64ToByteArray(String input){
        if(input == null){
            return null;
        }

        var decoder = Base64.getMimeDecoder();
        return decoder.decode(input);
    }

    @Named("toBase64")
    default String toString(byte[] input){
        if(input == null){
            return null;
        }

        var encoder = Base64.getEncoder();
        return encoder.encodeToString(input);
    }
}
