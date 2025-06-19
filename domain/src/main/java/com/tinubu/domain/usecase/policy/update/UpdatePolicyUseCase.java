package com.tinubu.domain.usecase.policy.update;

import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@RequiredArgsConstructor
@Component
public class UpdatePolicyUseCase implements UpdatePolicyApi {

    private final InsurancePersistenceSpi persistence;


    @Override
    public Policy execute(UpdatePolicyCommand command) {
        if (command.getId() == null || command.getName() == null || command.getStatus() == null) {
            throw new IllegalArgumentException("Invalid command parameters");
        }
        Policy existing = persistence.findById(command.getId())
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        Policy updated = existing.toBuilder()
                .name(command.getName())
                .status(command.getStatus())
                .startDate(command.getStartDate())
                .endDate(command.getEndDate())
                .updatedAt(Instant.now())
                .build();

        return persistence.update(updated);
    }
}
