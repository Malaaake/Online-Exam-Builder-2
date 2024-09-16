package pfe.exambuilder.service;

import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pfe.exambuilder.model.Role.RoleName;
import pfe.exambuilder.repository.RoleRepository;



@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findRoleByName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
    
    public enum RoleName {
        USER,
        ADMIN
    }
}
