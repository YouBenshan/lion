package com.cj.web.security;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(@ModelAttribute LoginCommand command) {
		return "login";
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

	@RequestMapping("/logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/admin";
	}
}
