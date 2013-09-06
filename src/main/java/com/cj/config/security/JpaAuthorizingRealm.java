package com.cj.config.security;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cj.domain.security.Account;
import com.cj.domain.security.Role;
import com.cj.repository.security.AccountRepository;

@Component
public class JpaAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private AccountRepository accountRepository;

	public JpaAuthorizingRealm() {
		setName("JpaAuthorizingRealm"); // This name must match the name in the
										// User class's getPrincipals() method
		setCredentialsMatcher(new HashedCredentialsMatcher("SHA-256"));
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		Account account = accountRepository
				.findByUsername(((UsernamePasswordToken) token).getUsername());
		if (account != null) {
			return new SimpleAuthenticationInfo(account.getId(),
					account.getPassword(), getName());
		} else {
			return null;
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Long accountId = (Long) principals.fromRealm(getName()).iterator()
				.next();
		Account account = accountRepository.findOne(accountId);
		if (account != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Role role : account.getRoles()) {
				info.addRole(role.getName());
				info.addStringPermissions(role.getPermissionsAsStringSet());
			}
			return info;
		} else {
			return null;
		}
	}

}
