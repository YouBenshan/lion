package com.cj.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.domain.security.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>,
		JpaSpecificationExecutor<Role> {

}
