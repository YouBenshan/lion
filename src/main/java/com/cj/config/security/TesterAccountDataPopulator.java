package com.cj.config.security;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.cj.domain.security.Account;
import com.cj.domain.security.Permission;
import com.cj.domain.security.Role;
import com.cj.repository.security.AccountHelper;
import com.cj.repository.security.RoleRepository;

@Slf4j
@Component
public class TesterAccountDataPopulator implements
		ApplicationListener<ContextRefreshedEvent> {
	private boolean notExcuted = true;

	@Autowired
	private AccountHelper accountHelper;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (notExcuted) {
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
			account.setUsername("itms");
			account.setPassword("itms");
			account.setEmail("itms@covisint.com");
			account.setRoles(roles);
			accountHelper.createAccount(account);

			// Set<Role> roles1 = new HashSet<Role>();
			// roles1.add(user);
			// for(int i=0;i<30; i++){
			// account = new Account();
			// account.setUsername("itms"+i);
			// account.setPassword("itms"+i);
			// account.setEmail("itms@covisint.com");
			// account.setRoles(roles1);
			// accountHelper.createAccount(account);
			//
			// }
		}

		notExcuted = false;
	}
}