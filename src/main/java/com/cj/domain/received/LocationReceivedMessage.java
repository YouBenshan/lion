package com.cj.domain.received;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue("location")
public class LocationReceivedMessage extends ReceivedMessage {

	private float locationX;

	private float locationY;

	private int scale;

	private String label;

	private long msgId;
}
