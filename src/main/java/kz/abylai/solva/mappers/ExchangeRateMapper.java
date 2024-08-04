package kz.abylai.solva.mappers;


import kz.abylai.solva.dtos.ExchangeRateDto;
import kz.abylai.solva.entities.ExchangeRate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {
    ExchangeRateDto toDto(ExchangeRate exchangeRate);
    ExchangeRate toEntity(ExchangeRateDto exchangeRateDto);
}
