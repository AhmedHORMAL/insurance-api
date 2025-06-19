package com.tinubu.infra;

import com.tinubu.domain.usecase.policy.Policy;
import com.tinubu.domain.usecase.policy.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsuranceAdapterTest {

    @Mock
    private PolicyRepository repository;

    @Mock
    private PolicyMapper mapper;

    @InjectMocks
    private InsuranceAdapter adapter;

    @Test
    void shouldSavePolicy() {
        Policy domain = samplePolicy(1L);
        PolicyEntity entity = sampleEntity(1L);

        when(mapper.toEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

        final var result = adapter.save(domain);

        assertThat(result).isEqualTo(domain);
        verify(repository).save(entity);
    }

    @Test
    void shouldFindPolicyById() {
        Long id = 1L;
        PolicyEntity entity = sampleEntity(id);
        Policy domain = samplePolicy(id);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        final var result = adapter.findById(id);

        assertThat(result).isPresent().contains(domain);
        verify(repository).findById(id);
    }

    @Test
    void shouldReturnAllPolicies() {
        PolicyEntity entity = sampleEntity(1L);
        Policy domain = samplePolicy(1L);

        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        final var policies = adapter.findAll();

        assertThat(policies).hasSize(1).containsExactly(domain);
        verify(repository).findAll();
    }

    @Test
    void shouldUpdatePolicy() {
        Policy domain = samplePolicy(2L);
        PolicyEntity entity = sampleEntity(2L);

        when(mapper.toEntity(domain)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDomain(entity)).thenReturn(domain);

       final var policy = adapter.update(domain);

        assertThat(policy).isEqualTo(domain);
        verify(repository).save(entity);
    }

    @Test
    void shouldReturnEmptyWhenPolicyNotFound() {
        // Given
        Long unknownId = 999L;
        when(repository.findById(unknownId)).thenReturn(Optional.empty());

        // When
        Optional<Policy> result = adapter.findById(unknownId);

        // Then
        assertThat(result).isEmpty();
        verify(repository).findById(unknownId);
        verifyNoInteractions(mapper);
    }

    private Policy samplePolicy(Long id) {
        return Policy.builder()
                .policyId(id)
                .name("Sample Policy")
                .status(Status.ACTIVE)
                .startDate(Instant.now())
                .endDate(Instant.now().plusSeconds(86400))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    private PolicyEntity sampleEntity(Long id) {
        return PolicyEntity.builder()
                .policyId(id)
                .name("Sample Policy")
                .status(PolicyEntity.Status.ACTIVE)
                .startDate(Instant.now())
                .endDate(Instant.now().plusSeconds(86400))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}
