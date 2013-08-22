package com.cj.domain.security;

public enum Permission {
	Profile_Role_A("add new Role"), Profile_Role_D("delete new Role");
	public String description;

	Permission(String description) {
		this.description = description;
	}

}
