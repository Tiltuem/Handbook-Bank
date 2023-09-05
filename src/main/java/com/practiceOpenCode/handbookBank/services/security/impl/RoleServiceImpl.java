package com.practiceOpenCode.handbookBank.services.security.impl;

import com.practiceOpenCode.handbookBank.models.security.Role;
import com.practiceOpenCode.handbookBank.repositories.security.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
