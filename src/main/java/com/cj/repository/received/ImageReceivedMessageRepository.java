package com.cj.repository.received;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.domain.received.ImageReceivedMessage;

@Repository
public interface ImageReceivedMessageRepository extends
		JpaRepository<ImageReceivedMessage, Long>,
		JpaSpecificationExecutor<ImageReceivedMessage> {

	public List<ImageReceivedMessage> findByOtherOrderByCreateTimeDesc(
			String other);

}
