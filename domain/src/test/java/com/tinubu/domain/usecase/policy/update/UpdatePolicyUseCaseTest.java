package com.tinubu.domain.usecase.policy.update;

import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import com.tinubu.domain.usecase.policy.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Instant;
import java.util.Optional;

import static com.tinubu.domain.usecase.policy.Status.ACTIVE;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdatePolicyUseCaseTest {

    private InsurancePersistenceSpi persistence;
    private UpdatePolicyUseCase useCase;

    @BeforeEach
    void setUp() {
        persistence = mock(InsurancePersistenceSpi.class);
        useCase = new UpdatePolicyUseCase(persistence);
    }

    @Test
    void shouldUpdatePolicySuccessfully() {
        // Given
        Long id = 1L;
        Instant oldTimestamp = Instant.parse("2024-01-01T00:00:00Z");
        Policy existing = Policy.builder()
                .policyId(id)
                .name("Old Name")
                .status(Status.INACTIVE)
                .startDate(Instant.parse("2024-01-01T00:00:00Z"))
                .endDate(Instant.parse("2025-01-01T00:00:00Z"))
                .createdAt(oldTimestamp)
                .updatedAt(oldTimestamp)
                .build();

        when(persistence.findById(id)).thenReturn(Optional.of(existing));

        UpdatePolicyCommand command = new UpdatePolicyCommand(
                id,
                "Updated Name",
                Status.ACTIVE,
                Instant.parse("2024-06-01T00:00:00Z"),
                Instant.parse("2025-06-01T00:00:00Z")
        );

        ArgumentCaptor<Policy> captor = ArgumentCaptor.forClass(Policy.class);
        when(persistence.update(any())).thenAnswer(inv -> inv.getArgument(0));

        // When
        Policy result = useCase.execute(command);

        // Then
        verify(persistence).update(captor.capture());
        Policy updated = captor.getValue();

        assertThat(result.getPolicyId()).isEqualTo(id);
        assertThat(updated.getName()).isEqualTo("Updated Name");
        assertThat(updated.getStatus()).isEqualTo(ACTIVE);
        assertThat(updated.getStartDate()).isEqualTo(command.getStartDate());
        assertThat(updated.getEndDate()).isEqualTo(command.getEndDate());
        assertThat(updated.getUpdatedAt()).isNotEqualTo(oldTimestamp);
    }

    @Test
    void shouldThrowExceptionIfCommandIsInvalid() {
        UpdatePolicyCommand invalidCommand = new UpdatePolicyCommand(null, null, null, null, null);

        assertThatThrownBy(() -> useCase.execute(invalidCommand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid command parameters");
    }

    @Test
    void shouldThrowExceptionIfPolicyNotFound() {
        Long id = 999L;
        when(persistence.findById(id)).thenReturn(Optional.empty());

        UpdatePolicyCommand command = new UpdatePolicyCommand(id, "name", Status.ACTIVE, Instant.now(), Instant.now());

        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Policy not found");
    }
}
