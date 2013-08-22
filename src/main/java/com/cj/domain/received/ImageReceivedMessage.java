package com.cj.domain.received;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("image")
@Setter
@Getter
public class ImageReceivedMessage extends ReceivedMessage {

	private String picUrl;

	private long msgId;
}
