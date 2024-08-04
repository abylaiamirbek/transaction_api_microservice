package kz.abylai.solva.services.impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.abylai.solva.repositories.ExchangeRateRepository;
import kz.abylai.solva.components.AlphaVantageService;
import kz.abylai.solva.dtos.ExchangeRateDto;
import kz.abylai.solva.entities.ExchangeRate;
import kz.abylai.solva.mappers.ExchangeRateMapper;
import kz.abylai.solva.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.exception.IOException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final AlphaVantageService alphaVantageService;
    private final ExchangeRateMapper exchangeRateMapper;

    @Scheduled(cron = "${CURRENCY_UPDATE_SCHEDULE}")
    public void updateDailyRates() {
        try {
            // Обновляем курс для валютной пары KZT/USD
            updateCurrencyPair("KZT");
            // Обновляем курс для валютной пары RUB/USD
            updateCurrencyPair("RUB");
        } catch (Exception e) {
            log.error("Failed to update daily rates", e);
            throw new RuntimeException("Failed to update daily rates: " + e.getMessage());
        }
    }

    private void updateCurrencyPair(String fromSymbol) {
        try {
            String response = alphaVantageService.fetchDailyFXData(fromSymbol, "USD");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode timeSeriesNode = rootNode.get("Time Series FX (Daily)");

            // Получаем последние данные о курсе закрытия по 4. close
            String latestDate = timeSeriesNode.fieldNames().next();
            JsonNode latestData = timeSeriesNode.get(latestDate);
            String closePrice = latestData.get("4. close").asText();
            BigDecimal rate = new BigDecimal(closePrice);

            ExchangeRateDto exchangeRateDto = new ExchangeRateDto(fromSymbol, "USD", LocalDate.now(), rate);
            ExchangeRate exchangeRate = exchangeRateMapper.toEntity(exchangeRateDto);
            exchangeRateRepository.save(exchangeRate);
            log.info("Currency pair {}-{} updated successfully", fromSymbol, "USD");
        } catch (JsonProcessingException e) {
            log.error("Failed to parse JSON response", e);
            throw new RuntimeException("Failed to parse JSON response: " + e.getMessage());
        } catch (IOException e) {
            log.error("Failed to update currency pair {}/{}", fromSymbol, "USD", e);
            throw new RuntimeException("Failed to update currency pair " + fromSymbol + "/" + "USD" + ": " + e.getMessage());
        }
    }
}
