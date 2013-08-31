package com.cj.repository.received;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.domain.received.ImageReceivedMessage;

@Repository
public interface ImageReceivedMessageRepository extends
		JpaRepository<ImageReceivedMessage, Long>,
		JpaSpecificationExecutor<ImageReceivedMessage> {
	
	public ImageReceivedMessage findByOther(String other);

}
