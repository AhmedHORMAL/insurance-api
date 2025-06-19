package com.tinubu.domain.usecase.policy.find;

import com.tinubu.domain.usecase.policy.Policy;

import java.util.List;

public interface ListAllPoliciesApi {

    List<Policy> execute();
}
