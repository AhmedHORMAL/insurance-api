package com.tinubu.domain.usecase.policy.create;

import com.tinubu.domain.usecase.policy.Status;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class CreatePolicyCommand {
    String name;
    Instant startDate;
    Instant endDate;
    Status status;
}
