package com.tinubu.app;

import com.tinubu.domain.usecase.policy.create.CreatePolicyApi;
import com.tinubu.domain.usecase.policy.create.CreatePolicyCommand;
import com.tinubu.domain.usecase.policy.find.GetPolicyByIdApi;
import com.tinubu.domain.usecase.policy.find.ListAllPoliciesUseCase;
import com.tinubu.domain.usecase.policy.update.UpdatePolicyApi;
import com.tinubu.domain.usecase.policy.update.UpdatePolicyCommand;
import com.tinubu.web.api.DefaultApi;
import com.tinubu.web.dto.Policy;
import com.tinubu.web.dto.PolicyInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PolicyResource implements DefaultApi {

    private final CreatePolicyApi createPolicy;
    private final UpdatePolicyApi updatePolicyApi;
    private final GetPolicyByIdApi getPolicyByIdApi;
    private final ListAllPoliciesUseCase listAllPolicies;



    @Override
    public ResponseEntity<Policy> policiesPost(@Valid PolicyInput policyInput) {

        var command = CreatePolicyCommand.builder()
                .name(policyInput.getName())
                .startDate(policyInput.getStartDate())
                .endDate(policyInput.getEndDate())
                .status(com.tinubu.domain.usecase.policy.Status.valueOf(policyInput.getStatus().name()))
                .build();

        com.tinubu.domain.usecase.policy.Policy created = createPolicy.execute(command);

        var response = new Policy()
                .id(created.getPolicyId())
                .name(created.getName())
                .status(Policy.StatusEnum.valueOf(created.getStatus().name()))
                .startDate(created.getStartDate())
                .endDate(created.getEndDate())
                .createdAt(created.getCreatedAt())
                .updatedAt(created.getUpdatedAt());

        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<Policy> policiesIdPut(Long id, @Valid PolicyInput policyInput) {
        var command = UpdatePolicyCommand.builder()
                .id(id)
                .name(policyInput.getName())
                .status(com.tinubu.domain.usecase.policy.Status.valueOf(policyInput.getStatus().name()))
                .startDate(policyInput.getStartDate())
                .endDate(policyInput.getEndDate())
                .build();

        var updated = updatePolicyApi.execute(command);

        var response = new Policy()
                .id(updated.getPolicyId())
                .name(updated.getName())
                .status(Policy.StatusEnum.valueOf(updated.getStatus().name()))
                .startDate(updated.getStartDate())
                .endDate(updated.getEndDate())
                .createdAt(updated.getCreatedAt())
                .updatedAt(updated.getUpdatedAt());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Policy> policiesIdGet(Long id) {
        Optional<com.tinubu.domain.usecase.policy.Policy> domainPolicy = getPolicyByIdApi.execute(id);
        if (domainPolicy.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Policy dto = new Policy()
                .id(domainPolicy.get().getPolicyId())
                .name(domainPolicy.get().getName())
                .status(Policy.StatusEnum.valueOf(domainPolicy.get().getStatus().name()))
                .startDate(domainPolicy.get().getStartDate())
                .endDate(domainPolicy.get().getEndDate())
                .createdAt(domainPolicy.get().getCreatedAt())
                .updatedAt(domainPolicy.get().getUpdatedAt());

        return ResponseEntity.ok(dto);
    }


    @Override
    public ResponseEntity<List<Policy>> policiesGet() {
        List<com.tinubu.domain.usecase.policy.Policy> domainPolicies = listAllPolicies.execute();

        List<Policy> policies = domainPolicies.stream().map(domain ->
                new Policy()
                        .id(domain.getPolicyId())
                        .name(domain.getName())
                        .status(Policy.StatusEnum.valueOf(domain.getStatus().name()))
                        .startDate(domain.getStartDate())
                        .endDate(domain.getEndDate())
                        .createdAt(domain.getCreatedAt())
                        .updatedAt(domain.getUpdatedAt())
        ).toList();

        return ResponseEntity.ok(policies);
    }
}
