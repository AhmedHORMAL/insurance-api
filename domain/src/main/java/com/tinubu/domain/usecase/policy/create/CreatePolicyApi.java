package com.tinubu.domain.usecase.policy.create;

import com.tinubu.domain.usecase.policy.Policy;

public interface CreatePolicyApi {
    Policy execute(CreatePolicyCommand command);
}