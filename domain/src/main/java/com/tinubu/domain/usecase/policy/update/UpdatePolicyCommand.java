package com.tinubu.domain.usecase.policy.update;

import com.tinubu.domain.usecase.policy.Status;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class UpdatePolicyCommand {
    Long id;
    String name;
    Status status;
    Instant startDate;
    Instant endDate;
}