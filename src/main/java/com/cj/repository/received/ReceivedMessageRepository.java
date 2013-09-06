package com.cj.repository.received;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.cj.domain.received.ReceivedMessage;

@Repository
public interface ReceivedMessageRepository extends
		JpaRepository<ReceivedMessage, Long>,
		JpaSpecificationExecutor<ReceivedMessage> {

	@QueryHints(forCounting = false)
	@Query(value = "SELECT NEW com.cj.repository.received.ActiveInfo(m.other, COUNT(m) AS c) FROM ReceivedMessage m GROUP BY m.other ORDER BY c DESC", countQuery = "SELECT Count(m) FROM ReceivedMessage m GROUP BY m.other")
	Page<ActiveInfo> findActive(Pageable pageable);

}
