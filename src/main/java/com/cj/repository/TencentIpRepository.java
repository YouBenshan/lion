package com.cj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cj.domain.TencentIp;

@Repository
public interface TencentIpRepository extends JpaRepository<TencentIp, Long>,
		JpaSpecificationExecutor<TencentIp> {
}
