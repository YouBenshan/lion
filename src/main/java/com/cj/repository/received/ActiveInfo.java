package com.cj.repository.received;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActiveInfo {
	private String other;
	private long mount;

	public ActiveInfo(String other, long mount) {
		super();
		this.other = other;
		this.mount = mount;
	}
}