package com.tinubu.domain.usecase.policy.find;

import com.tinubu.domain.usecase.policy.Policy;

import java.util.Optional;

public interface GetPolicyByIdApi {
    Optional<Policy> execute(Long id);
}
