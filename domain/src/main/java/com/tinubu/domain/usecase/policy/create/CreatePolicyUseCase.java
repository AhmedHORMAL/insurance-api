package com.tinubu.domain.usecase.policy.create;

import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import com.tinubu.domain.usecase.policy.PolicyIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
public class CreatePolicyUseCase implements CreatePolicyApi {

    private final InsurancePersistenceSpi persistence;

    @Override
    public Policy execute(CreatePolicyCommand command) {
        if (command.getName() == null || command.getName().isBlank()) {
            throw new IllegalArgumentException("The policy name is required.");
        }
        if (command.getStartDate() == null || command.getEndDate() == null) {
            throw new IllegalArgumentException("Coverage dates are required.");
        }
        if (command.getStartDate().isAfter(command.getEndDate())) {
            throw new IllegalArgumentException("The start date cannot be after the end date.");
        }

        var now = Instant.now();

        Policy newPolicy = Policy.builder()
                .policyId(PolicyIdGenerator.generate())
                .name(command.getName())
                .status(command.getStatus())
                .startDate(command.getStartDate())
                .endDate(command.getEndDate())
                .createdAt(now)
                .updatedAt(now)
                .build();

        return persistence.save(newPolicy);
    }
}
