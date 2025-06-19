package com.tinubu.domain.usecase.policy.update;

import com.tinubu.domain.usecase.policy.Policy;

public interface UpdatePolicyApi {
    Policy execute(UpdatePolicyCommand command);
}
