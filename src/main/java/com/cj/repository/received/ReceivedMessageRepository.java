package com.cj.repository.received;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.domain.received.ReceivedMessage;

@Repository
public interface ReceivedMessageRepository extends
		JpaRepository<ReceivedMessage, Long>,
		JpaSpecificationExecutor<ReceivedMessage> {

}
