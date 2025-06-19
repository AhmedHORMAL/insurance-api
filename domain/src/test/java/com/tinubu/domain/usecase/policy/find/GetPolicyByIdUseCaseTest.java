package com.tinubu.domain.usecase.policy.find;

import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static com.tinubu.domain.usecase.policy.Status.ACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPolicyByIdUseCaseTest {

    @Mock
    InsurancePersistenceSpi persistence;

    @InjectMocks
    GetPolicyByIdUseCase useCase;

    @Test
    void shouldReturnPolicyWhenExists() {
        // Given
        Long id = 123L;
        Instant now = Instant.now();
        Policy policy = Policy.builder()
                .policyId(id)
                .name("Test Policy")
                .status(ACTIVE)
                .startDate(now)
                .endDate(now.plusSeconds(3600))
                .createdAt(now)
                .updatedAt(now)
                .build();

        when(persistence.findById(id)).thenReturn(Optional.of(policy));

        // When
        Optional<Policy> result = useCase.execute(id);

        // Then
        assertTrue(result.isPresent());
        assertEquals(policy, result.get());
    }

    @Test
    void shouldReturnEmptyWhenPolicyNotFound() {
        // Given
        Long id = 999L;
        when(persistence.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<Policy> result = useCase.execute(id);

        // Then
        assertTrue(result.isEmpty());
    }
}
