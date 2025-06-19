package com.tinubu.domain.usecase.policy.find;

import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GetPolicyByIdUseCase implements GetPolicyByIdApi {
    private final InsurancePersistenceSpi persistence;

    @Override
    public Optional<Policy> execute(Long id) {
        return persistence.findById(id);
    }
}

