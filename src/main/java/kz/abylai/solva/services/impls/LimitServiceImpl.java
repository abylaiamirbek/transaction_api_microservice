package kz.abylai.solva.services.impls;

import kz.abylai.solva.repositories.LimitRepository;
import kz.abylai.solva.services.LimitService;
import kz.abylai.solva.dtos.LimitDto;
import kz.abylai.solva.entities.Limits;
import kz.abylai.solva.mappers.LimitMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;
    private final LimitMapper limitMapper;

    @Override
    public void setNewLimit(Limits.Category category, BigDecimal newLimit) {
        try {
            Limits newLimitObject = new Limits();
            newLimitObject.setCategory(category);
            newLimitObject.setMonthlyLimitUSD(newLimit);
            newLimitObject.setSetDateTime(LocalDateTime.now());
            limitRepository.save(newLimitObject);
            log.info("New limit set successfully for category {}: {}", category, newLimit);
        } catch (Exception e) {
            log.error("Failed to set new limit", e);
            throw new RuntimeException("Failed to set new limit: " + e.getMessage());
        }
    }


    @Override
    public List<LimitDto> getAllLimits() {
        try {
            List<Limits> limits = limitRepository.findAll();
            List<LimitDto> limitDtos = new ArrayList<>();
            for (Limits limit : limits) {
                limitDtos.add(limitMapper.toDto(limit));
            }
            log.info("Retrieved all limits successfully");
            return limitDtos;
        } catch (Exception e) {
            log.error("Failed to get all limits", e);
            throw new RuntimeException("Failed to get all limits: " + e.getMessage());
        }
    }
}
