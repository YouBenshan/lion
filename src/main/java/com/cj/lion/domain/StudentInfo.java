package com.cj.lion.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.cj.domain.IdEntity;

@Setter
@Getter
@Entity
public class StudentInfo extends IdEntity {
	@Size(min = 1)
	private String wechatId;
	@Size(min = 1)
	private String name;
	@Size(min = 1)
	private String school;
	@Size(min = 1)
	private String mobile;
	private boolean stored = false;
}
