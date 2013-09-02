package com.cj.config.security;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.cj.config.AppProperties;
import com.cj.domain.security.Account;
import com.cj.domain.security.Permission;
import com.cj.domain.security.Role;
import com.cj.repository.security.RoleRepository;

@Slf4j
@Component
public class TesterAccountDataPopulator implements
		ApplicationListener<ContextRefreshedEvent> {
	private boolean notExcuted = true;
	
	@Autowired
	private AppProperties appProperties;

	@Autowired
	private AccountHelper accountHelper;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (notExcuted) {
			try {
				Set<Permission> permissions = new HashSet<Permission>();
				permissions.add(Permission.Profile_Role_A);
				permissions.add(Permission.Profile_Role_D);

				Role admin = new Role();
				admin.setName("admin");
				admin.setPermissions(permissions);
				roleRepository.save(admin);

				Role user = new Role();
				user.setName("user");
				user.setPermissions(new HashSet<Permission>());
				roleRepository.save(user);

				Set<Role> roles = new HashSet<Role>();
				roles.add(admin);
				roles.add(user);

				Account account = new Account();
				account.setUsername(appProperties.getUsername());
				account.setPassword(appProperties.getPassword());
				account.setEmail("u122769@compuware.com");
				account.setRoles(roles);
				accountHelper.createAccount(account);
			} catch (DataIntegrityViolationException e) {
				log.debug("havd create the account before", e);
			}
		}

		notExcuted = false;
	}
}