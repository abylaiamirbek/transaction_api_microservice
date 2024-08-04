package kz.abylai.solva.mappers;

import kz.abylai.solva.dtos.TransactionDto;
import kz.abylai.solva.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto toDto(Transaction transaction);
    Transaction toEntity(TransactionDto transactionDto);
}
