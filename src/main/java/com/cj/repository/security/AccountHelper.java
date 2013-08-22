package com.cj.repository.security;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cj.domain.security.Account;

@Component
public class AccountHelper {
	@Autowired
	private AccountRepository accountRepository;

	// @Autowired
	// private MailSender mailSender;
	public void createAccount(Account account) {
		String password = account.getPassword();
		account.setPassword(new Sha256Hash(password).toHex());
		accountRepository.save(account);

		// SimpleMailMessage mailMessage = new SimpleMailMessage();
		// mailMessage.setFrom("admin.itms@covisint.com");
		// mailMessage.setSubject("account created");
		// mailMessage.setTo(account.getEmail());
		// mailMessage.setText(account.getUsername()+" "+password);
		// mailSender.send(mailMessage);
	}
}
