package com.cj.domain.security;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Setter
@Getter
@Entity
public class Account extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2040317608235092271L;

	@NotNull
	@Size(min = 4)
	@Column(unique = true, nullable = false)
	private String username;

	private String email;

	@Basic(optional = false)
	@Column(length = 255)
	private String password;

	@ManyToMany
	private Set<Role> roles;
}
