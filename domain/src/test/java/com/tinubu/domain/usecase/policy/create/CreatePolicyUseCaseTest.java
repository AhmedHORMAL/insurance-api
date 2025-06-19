package com.tinubu.domain.usecase.policy.create;

import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import com.tinubu.domain.usecase.policy.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
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
        var name = "Policy A";
        var startDate = Instant.parse("2025-01-01T00:00:00Z");
        var endDate = Instant.parse("2025-12-31T00:00:00Z");

        var command = CreatePolicyCommand.builder()
                .name(name)
                .startDate(startDate)
                .endDate(endDate)
                .status(Status.ACTIVE)
                .build();

        when(persistence.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Policy result = useCase.execute(command);

        // Then
        assertNotNull(result);
        assertNotNull(result.getPolicyId(), "Policy ID should not be null");
        assertTrue(result.getPolicyId() > 0, "Policy ID should be positive");

        assertEquals(name, result.getName());
        assertEquals(Status.ACTIVE, result.getStatus());

        assertEquals(startDate, result.getStartDate());
        assertEquals(endDate, result.getEndDate());

        assertNotNull(result.getCreatedAt(), "createdAt should not be null");
        assertNotNull(result.getUpdatedAt(), "updatedAt should not be null");
        assertEquals(result.getCreatedAt(), result.getUpdatedAt(), "createdAt and updatedAt should be equal at creation");

        verify(persistence).save(any());
    }


    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        var command = CreatePolicyCommand.builder()
                .name(" ")
                .startDate(Instant.now())
                .endDate(Instant.now().plusSeconds(3600))
                .build();

        var ex = assertThrows(IllegalArgumentException.class, () -> useCase.execute(command));
        assertEquals("The policy name is required.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDatesAreNull() {
        var command = CreatePolicyCommand.builder()
                .name("Policy B")
                .startDate(null)
                .endDate(null)
                .build();

        var ex = assertThrows(IllegalArgumentException.class, () -> useCase.execute(command));
        assertEquals("Coverage dates are required.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenStartDateAfterEndDate() {
        var command = CreatePolicyCommand.builder()
                .name("Policy C")
                .startDate(Instant.parse("2025-12-31T00:00:00Z"))
                .endDate(Instant.parse("2025-01-01T00:00:00Z"))
                .build();

        var ex = assertThrows(IllegalArgumentException.class, () -> useCase.execute(command));
        assertEquals("The start date cannot be after the end date.", ex.getMessage());
    }
}
