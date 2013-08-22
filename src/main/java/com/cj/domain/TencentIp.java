package com.cj.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class TencentIp extends IdEntity{

	private String ip;
}
