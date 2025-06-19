package com.tinubu.infra;

import com.tinubu.domain.usecase.policy.Policy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PolicyMapper {
    PolicyEntity toEntity(Policy policy);
    Policy toDomain(PolicyEntity policyEntity);
}
