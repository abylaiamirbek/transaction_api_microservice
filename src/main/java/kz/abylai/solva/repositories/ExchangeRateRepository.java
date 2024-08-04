package kz.abylai.solva.repositories;

import jakarta.transaction.Transactional;
import kz.abylai.solva.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@Transactional
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    ExchangeRate findByDateAndBaseCurrencyAndTargetCurrency(LocalDate date, String baseCurrency,
                                                            String TargetCurrency);
}
