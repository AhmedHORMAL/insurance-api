package com.tinubu.infra;

import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InsuranceAdapter implements InsurancePersistenceSpi {
    private final PolicyRepository jpaRepository;
    private final PolicyMapper mapper;

    @Override
    public Policy save(Policy policy) {
        var savedPolicy = jpaRepository.save(mapper.toEntity(policy));
        return mapper.toDomain(savedPolicy);
    }

    @Override
    public Optional<Policy> findById(Long policyId) {
        return jpaRepository.findById(policyId).map(mapper::toDomain);
    }

    @Override
    public List<Policy> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Policy update(Policy policy) {
        var savedPolicy = jpaRepository.save(mapper.toEntity(policy));
        return mapper.toDomain(savedPolicy);
    }
}
