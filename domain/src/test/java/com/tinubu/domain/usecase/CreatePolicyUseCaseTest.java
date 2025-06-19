package com.tinubu.domain.usecase;

import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import com.tinubu.domain.usecase.policy.create.CreatePolicyCommand;
import com.tinubu.domain.usecase.policy.create.CreatePolicyUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePolicyUseCaseTest {

    @Mock
    InsurancePersistenceSpi persistence;

    @InjectMocks
    CreatePolicyUseCase useCase;

    @Test
    void shouldCreatePolicySuccessfully() {
        // Given
        var command = CreatePolicyCommand.builder()
                .name("Assurance Habitation")
                .startDate(Instant.parse("2024-01-01T00:00:00Z"))
                .endDate(Instant.parse("2025-01-01T00:00:00Z"))
                .build();

        when(persistence.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Policy result = useCase.execute(command);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getPolicyId()).isNotNull();
        assertThat(result.getName()).isEqualTo(command.getName());
        assertThat(result.getStatus()).isEqualTo(command.getStatus());
        assertThat(result.getCreatedAt()).isNotNull();
        assertThat(result.getUpdatedAt()).isNotNull();

        verify(persistence).save(any());
    }

    @Test
    void shouldThrowWhenNameIsBlank() {
        var command = CreatePolicyCommand.builder()
                .name(" ")
                .startDate(Instant.now())
                .endDate(Instant.now().plusSeconds(3600))
                .build();

        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The policy name is required.");
    }

    @Test
    void shouldThrowWhenDatesAreNull() {
        var command = CreatePolicyCommand.builder()
                .name("Assurance")
                .startDate(null)
                .endDate(null)
                .build();

        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Coverage dates are required.");
    }

    @Test
    void shouldThrowWhenStartDateAfterEndDate() {
        var command = CreatePolicyCommand.builder()
                .name("Assurance")
                .startDate(Instant.parse("2025-01-01T00:00:00Z"))
                .endDate(Instant.parse("2024-01-01T00:00:00Z"))
                .build();

        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The start date cannot be after the end date.");
    }
}
