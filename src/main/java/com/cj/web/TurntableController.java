package com.cj.web;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.cj.domain.LotteryRecord;
import com.cj.domain.LotterySetUp;
import com.cj.domain.Prize;
import com.cj.domain.PrizeItem;
import com.cj.repository.LotteryRecordRepository;
import com.cj.repository.LotterySetUpRepository;
import com.cj.repository.PrizeItemRepository;
import com.cj.utils.LotteryUtil;

@Controller
public class TurntableController 
{
	@Autowired
	private LotterySetUpRepository lotterySetUpRepository;
	
	@Autowired
	private PrizeItemRepository prizeItemRepository;
	
	@Autowired
	private LotteryRecordRepository lotteryRecordRepository;
	
	private int currentTotalNum = 0;

	@RequestMapping(value = "/activity/turntable", method = RequestMethod.GET)
    @ResponseBody
	 public Prize getPrizeData(ServletRequest request, String wechatId, String userType, int tid)
	 {
	        System.out.println("wechatId===============================>>>>>>>>> "+wechatId);
	    	
			LotteryRecord record = lotteryRecordRepository.findByWechatIdAndLotteryID(wechatId, (long)tid);
	        Prize prize = new Prize();
	        
	        if(LotteryUtil.isContinue(record, 4, 10))//可以抽奖
	        {
	        	if(LotteryUtil.isMayLucky(record))// 该活动用户没有中过奖，则可以中奖
	        	{
	        		String snCode = null; 
	        		
	        		//每10000次抽奖中一次一等奖
	        		if(currentTotalNum%10000 == 0)
	        		{
	        			snCode = LotteryUtil.generatorSN();
	        			prize = LotteryUtil.setThePrize(prize, 1, snCode);
	        			saveOrUpdateLotteryRecord(record,wechatId, tid,snCode,true);
	        		}
	        		else if(currentTotalNum%1000 == 0)//每1000次抽奖中二等奖
	        		{
	        			snCode = LotteryUtil.generatorSN();
	        			prize = LotteryUtil.setThePrize(prize, 2, snCode);
	        			saveOrUpdateLotteryRecord(record,wechatId, tid,snCode,true);
	        		}
	        		else if(currentTotalNum%10 == 0)//每10次抽奖中三等奖
	        		{
	        			snCode = LotteryUtil.generatorSN();
	        			prize = LotteryUtil.setThePrize(prize, 3, snCode);	
	        			saveOrUpdateLotteryRecord(record,wechatId, tid,snCode,true);
	        		}
	        		else
	        		{
	        			saveOrUpdateLotteryRecord(record,wechatId, tid,snCode,false);
	        		}	        				
	        	}
	
	        }else{//超过最大抽奖次数
	        	prize.setError("invalid");
	        	prize.setMsg("超过当天的最大抽奖次数");
	        }

	        return prize;
	 }
    
	private void saveOrUpdateLotteryRecord(LotteryRecord record,String wechatId, int tid,
			String snCode, boolean isLucky)
	{
		record.setSn(snCode);
		record.setLastDateTime(new Date());
		record.setWechatId(wechatId);
		record.setLotteryID((long)tid);
		
		int dayNum = (record == null? 1:(record.getDayNum()+1));
		int totalNum = (record == null? 1:(record.getTotalNum()+1));
		
        record.setDayNum(dayNum);
	    record.setTotalNum(totalNum);
	    
	    //中奖
	    if(isLucky == true)
	    	record.setState("2");
 
		lotteryRecordRepository.save(record);
	}
   
    @RequestMapping(value = "/activity/initdata", method = RequestMethod.GET)
    @ResponseBody
    public void insertInitData(ServletRequest request) throws Exception
    {
    	System.out.println("开始插入数据LotterySetUp Table ===============================>>>>>>>>>");
    	
    	LotterySetUp setup = new LotterySetUp();
    	setup.setLotteryID((long) 1);
    	setup.setLotteryName("大转盘");
    	
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
    	setup.setBeginTime(dateformat.parse("2013-09-28"));
    	setup.setEndTime(dateformat.parse("2013-10-28"));
    	setup.setLotteryExp(dateformat.parse("2013-11-15"));
    	
    	setup.setDayMaxPriz(10);
    	setup.setPayPersonNum(10);
    	setup.setPayDayNum(10);
    	setup.setEstimateNum(100);
    	setup.setChangeNum(10);
    	setup.setPayDayState(true);
    	setup.setPayDayState(false);
    	setup.setMaxNum(20);
    	setup.setMaxNumDay(5);
    	setup.setState("开始");
    	
    	lotterySetUpRepository.save(setup);
    	
    	System.out.println("开始插入数据LotterySetUp Table ===============================>>>>>>>>>");
    	
    	PrizeItem item = new PrizeItem();
    	item.setLotteryID((long)1);
    	item.setItemId(1);
    	item.setItemDesc("一等奖");
    	item.setPrizeNum(1);
    	item.setRemainNum(1);
    	
    	prizeItemRepository.save(item);
    	
    	PrizeItem item1 = new PrizeItem();
    	
    	item1.setLotteryID((long)2);
    	item1.setItemId(2);
    	item1.setItemDesc("二等奖");
    	item1.setPrizeNum(2);
    	item1.setRemainNum(2);    	
    	
    	prizeItemRepository.save(item1);

    	PrizeItem item2 = new PrizeItem();
    	
    	item2.setLotteryID((long)3);
    	item2.setItemId(3);
    	item2.setItemDesc("三等奖");
    	item2.setPrizeNum(3);
    	item2.setRemainNum(3);    	
    	
    	prizeItemRepository.save(item2);
    	
    	System.out.println("开始插入数据LotteryRecord Table ===============================>>>>>>>>>");
    	
    	LotteryRecord record1 = new LotteryRecord();
    	record1.setLotteryID((long)1);
    	record1.setWechatId("o7MB9jpybk-XJZHjQfjeM51i5XpI");
    	record1.setState("0");
    	record1.setDayNum(3);
    	record1.setTotalNum(5);
    	record1.setLastDateTime(dateformat.parse("2013-11-15"));

    	lotteryRecordRepository.save(record1);   	
    }
}
