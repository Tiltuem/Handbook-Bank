package com.practiceOpenCode.handbookBank.services.security.impl;

import com.practiceOpenCode.handbookBank.models.security.Role;
import com.practiceOpenCode.handbookBank.repositories.security.RoleRepository;
import com.practiceOpenCode.handbookBank.services.security.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
