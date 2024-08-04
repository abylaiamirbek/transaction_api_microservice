package kz.abylai.solva.services;

import kz.abylai.solva.dtos.LimitDto;
import kz.abylai.solva.entities.Limits;

import java.math.BigDecimal;
import java.util.List;

public interface LimitService {

    void setNewLimit(Limits.Category category, BigDecimal newLimit);
    List<LimitDto> getAllLimits();
}
