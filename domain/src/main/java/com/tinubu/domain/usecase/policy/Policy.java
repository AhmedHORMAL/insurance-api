package com.tinubu.domain.usecase.policy;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Value
@Builder(builderClassName = "Builder", toBuilder = true)
public class Policy {
    Long policyId;
    String name;
    Status status;
    Instant startDate;
    Instant endDate;
    Instant createdAt;
    Instant updatedAt;


}
