package kz.abylai.solva.components;

import kz.abylai.solva.feigns.AlphaVantageFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlphaVantageService {

    private final AlphaVantageFeignClient alphaVantageFeignClient;

    public String fetchDailyFXData(String fromSymbol, String toSymbol) {
        try {
            String function = "FX_DAILY";
            String apiKey = "8KVB0PTWXESRVW65";
            return alphaVantageFeignClient.fetchDailyFXData(function, fromSymbol, toSymbol, apiKey);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch daily FX data: " + e.getMessage());
        }
    }
}
