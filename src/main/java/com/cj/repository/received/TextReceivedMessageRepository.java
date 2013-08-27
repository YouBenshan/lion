package com.cj.repository.received;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cj.domain.received.TextReceivedMessage;

public interface TextReceivedMessageRepository extends
		JpaRepository<TextReceivedMessage, Long>,
		JpaSpecificationExecutor<TextReceivedMessage> {
}
