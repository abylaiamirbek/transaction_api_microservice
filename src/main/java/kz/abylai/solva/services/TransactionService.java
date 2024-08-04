package kz.abylai.solva.services;

import kz.abylai.solva.dtos.TransactionDto;
import java.util.List;

public interface TransactionService {

    boolean saveTransaction(TransactionDto transactionDto);
    List<TransactionDto> getTransactionsExceedingLimit();
}
