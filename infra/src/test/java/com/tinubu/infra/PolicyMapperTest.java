package com.tinubu.infra;

import com.tinubu.domain.usecase.policy.Policy;
import com.tinubu.domain.usecase.policy.Status;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class PolicyMapperTest {

    private final PolicyMapper mapper = Mappers.getMapper(PolicyMapper.class);

    @Test
    void shouldMapDomainToEntity() {
        Policy domain = Policy.builder()
                .policyId(123L)
                .name("Assurance Habitation")
                .status(Status.ACTIVE)
                .startDate(Instant.parse("2024-01-01T00:00:00Z"))
                .endDate(Instant.parse("2025-01-01T00:00:00Z"))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        final PolicyEntity entity = mapper.toEntity(domain);

        assertThat(entity.getPolicyId()).isEqualTo(domain.getPolicyId());
        assertThat(entity.getName()).isEqualTo(domain.getName());
        assertThat(entity.getStatus().name()).isEqualTo(domain.getStatus().name());
    }

    @Test
    void shouldMapEntityToDomain() {
        PolicyEntity entity = PolicyEntity.builder()
                .policyId(456L)
                .name("Assurance Auto")
                .status(PolicyEntity.Status.INACTIVE)
                .startDate(Instant.parse("2024-02-01T00:00:00Z"))
                .endDate(Instant.parse("2025-02-01T00:00:00Z"))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        final Policy domain = mapper.toDomain(entity);

        assertThat(domain.getPolicyId()).isEqualTo(entity.getPolicyId());
        assertThat(domain.getName()).isEqualTo(entity.getName());
        assertThat(domain.getStatus().name()).isEqualTo(entity.getStatus().name());
    }

}
