package com.cj.web;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cj.config.AppProperties;
import com.cj.config.security.AccountHelper;
import com.cj.domain.security.Account;
import com.cj.domain.security.Permission;
import com.cj.domain.security.Role;
import com.cj.repository.security.RoleRepository;

@Slf4j
@Controller
public class SecurityController {
	@Autowired
	private AppProperties appProperties;

	@Autowired
	private AccountHelper accountHelper;

	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping("/initData")
	public String initData() {
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

		} catch (Exception e) {
			log.info("data havd been init before");
		}
		return "redirect: /login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@Valid LoginCommand command, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return loginForm(command);
		}

		UsernamePasswordToken token = new UsernamePasswordToken(
				command.getUsername(), command.getPassword(),
				command.isRememberMe());
		try {
			SecurityUtils.getSubject().login(token);
		} catch (AuthenticationException e) {
			bindingResult.reject("error.login.generic",
					"Invalid username or password.  Please try again.");
			return this.loginForm(command);

		}

		return "redirect:/admin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(@ModelAttribute LoginCommand command) {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/admin";
	}
}
