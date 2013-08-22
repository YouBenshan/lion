package com.cj.repository.received;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.domain.received.LocationReceivedMessage;

@Repository
public interface LocationReceivedMessageRepository extends
		JpaRepository<LocationReceivedMessage, Long>,
		JpaSpecificationExecutor<LocationReceivedMessage> {

}
