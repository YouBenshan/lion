package com.cj.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cj.domain.LotteryRecord;

public interface LotteryRecordRepository extends JpaRepository<LotteryRecord, Long>, JpaSpecificationExecutor<LotteryRecord> 
{
	LotteryRecord findByWechatIdAndLotteryID(String wechatId, long lotteryID);
}
