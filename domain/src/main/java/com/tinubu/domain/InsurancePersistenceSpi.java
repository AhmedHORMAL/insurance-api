package com.tinubu.domain;

import com.tinubu.domain.usecase.policy.Policy;

import java.util.List;
import java.util.Optional;

public interface InsurancePersistenceSpi {
    Policy save(Policy policy);
    Optional<Policy> findById(Long id);
    List<Policy> findAll();
    Policy update(Policy policy);
}
