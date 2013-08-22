package com.cj.domain.received;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue("link")
public class LinkReceivedMessage extends ReceivedMessage {

	private String title;

	private String description;

	private String url;

	private long msgId;
}
