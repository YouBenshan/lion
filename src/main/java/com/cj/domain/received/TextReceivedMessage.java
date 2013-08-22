package com.cj.domain.received;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue("text")
public class TextReceivedMessage extends ReceivedMessage {

	private String content;

	private long msgId;
}
