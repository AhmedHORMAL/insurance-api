package com.tinubu.domain.usecase.policy.find;


import com.tinubu.domain.InsurancePersistenceSpi;
import com.tinubu.domain.usecase.policy.Policy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListAllPoliciesUseCase implements ListAllPoliciesApi {

    private final InsurancePersistenceSpi persistence;

    public List<Policy> execute() {
        return persistence.findAll();
    }
}
