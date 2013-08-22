package com.cj.repository.received;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.domain.received.EventReceivedMessage;

@Repository
public interface EventReceivedMessageRepository extends
		JpaRepository<EventReceivedMessage, Long>,
		JpaSpecificationExecutor<EventReceivedMessage> {

}
