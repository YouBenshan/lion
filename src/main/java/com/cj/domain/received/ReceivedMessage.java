package com.cj.domain.received;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import com.cj.domain.IdEntity;

@Setter
@Getter
@Entity
public class ReceivedMessage extends IdEntity {

	private int createTime;

	private String other;
}
