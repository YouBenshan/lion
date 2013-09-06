package com.cj.repository.received;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.domain.received.EventReceivedMessage;

@Repository
public interface EventReceivedMessageRepository extends
		JpaRepository<EventReceivedMessage, Long>,
		JpaSpecificationExecutor<EventReceivedMessage> {

	Page<EventReceivedMessage> findByEventAndCreateTimeGreaterThan(
			String event, int createTime, Pageable pageable);
}
