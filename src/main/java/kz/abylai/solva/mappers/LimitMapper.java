package kz.abylai.solva.mappers;


import kz.abylai.solva.dtos.LimitDto;
import kz.abylai.solva.entities.Limits;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LimitMapper {
    LimitDto toDto(Limits limit);
    Limits toEntity(LimitDto limitDto);
}
