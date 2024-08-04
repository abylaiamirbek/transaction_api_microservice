package kz.abylai.solva.repositories;

import jakarta.transaction.Transactional;
import kz.abylai.solva.entities.Limits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Transactional
public interface LimitRepository extends JpaRepository<Limits, Long> {

    Limits findFirstByCategoryAndSetDateTimeBetweenOrderBySetDateTimeDesc(Limits.Category category,
                                                                  LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
