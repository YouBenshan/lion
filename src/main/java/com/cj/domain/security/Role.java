package com.cj.domain.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Setter
@Getter
@Entity
public class Role extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9187047908263736817L;

	private String name;

	private String description;

	@ElementCollection
	private Set<Permission> permissions;

	public Set<String> getPermissionsAsStringSet() {
		Set<String> set = new HashSet<String>();
		for (Permission permission : permissions) {
			set.add(permission.name());
		}
		return set;
	}

}
