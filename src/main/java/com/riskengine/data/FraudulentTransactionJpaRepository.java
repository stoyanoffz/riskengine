package com.riskengine.data;

import com.riskengine.domain.FraudulentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FraudulentTransactionJpaRepository extends JpaRepository<FraudulentTransactionEntity, Long> {

    List<FraudulentTransactionEntity> findAllByUserIdAndTimestampBetween(
            String userId,
            LocalDateTime from,
            LocalDateTime to
    );

    Long countByUserIdAndTimestampBetween(String userId, LocalDateTime from, LocalDateTime to);

    Long countDistinctByUserIdAndTimestampBetween(String userId, LocalDateTime from, LocalDateTime to);

}
