package com.tinubu.domain.usecase.policy.find;

import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static com.tinubu.domain.usecase.policy.Status.ACTIVE;
import static com.tinubu.domain.usecase.policy.Status.INACTIVE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListAllPoliciesUseCaseTest {

    @Mock
    InsurancePersistenceSpi persistence;

    @InjectMocks
    ListAllPoliciesUseCase useCase;

    @Test
    void shouldReturnAllPolicies() {
        // Given
        Instant now = Instant.now();

        Policy policy1 = Policy.builder()
                .policyId(1L)
                .name("Policy 1")
                .status(ACTIVE)
                .startDate(now)
                .endDate(now.plusSeconds(3600))
                .createdAt(now)
                .updatedAt(now)
                .build();

        Policy policy2 = Policy.builder()
                .policyId(2L)
                .name("Policy 2")
                .status(INACTIVE)
                .startDate(now)
                .endDate(now.plusSeconds(7200))
                .createdAt(now)
                .updatedAt(now)
                .build();

        when(persistence.findAll()).thenReturn(List.of(policy1, policy2));

        // When
        List<Policy> result = useCase.execute();

        // Then
        assertEquals(2, result.size());
        assertTrue(result.contains(policy1));
        assertTrue(result.contains(policy2));
        verify(persistence, times(1)).findAll();
    }
}
