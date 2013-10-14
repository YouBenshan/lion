package com.cj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cj.domain.LotterySetUp;

public interface LotterySetUpRepository extends
JpaRepository<LotterySetUp, Long>,
JpaSpecificationExecutor<LotterySetUp> {
}
