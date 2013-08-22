package com.cj.repository.received;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.domain.received.LinkReceivedMessage;

@Repository
public interface LinkReceivedMessageRepository extends
		JpaRepository<LinkReceivedMessage, Long>,
		JpaSpecificationExecutor<LinkReceivedMessage> {

}
