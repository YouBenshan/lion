package com.cj.repository.received;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActiveInfo{
	public ActiveInfo(String other, long mount) {
		super();
		this.other = other;
		this.mount = mount;
	}
	private String other;
	private long mount;
}