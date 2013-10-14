package com.cj.utils;

import java.text.SimpleDateFormat;
import java.util.Random;

import com.cj.domain.LotteryRecord;
import com.cj.domain.Prize;

public class LotteryUtil
{
	public static final char fontBase_english[] = {'A' ,'B' ,'C' ,'D' ,'E' ,'F' ,'G' ,'H' ,'I' ,'J' ,'K' ,'L'};
	
	public static String generatorSN()
    {   	
    	SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmssSSS");
		String timeMillisSequence=sdf.format(System.currentTimeMillis());
		
        StringBuffer sb = new StringBuffer();  
        Random r = new Random(); 
        
        for(int i=2;i>0;i--)
        {
        	sb.append(fontBase_english[(r.nextInt(25))]);
        }
 
        String snCode = timeMillisSequence+sb.toString()+"M";
		
    	return snCode;
    }
	
	public static boolean isContinue(LotteryRecord record, int dayNum, int totalNum)
    {
    	if(record !=null)
    	{
        	int countDayNum = record.getDayNum();
        	int countTotalNum = record.getTotalNum();
        	
        	if(countDayNum<dayNum&&countTotalNum<totalNum)
        		return true;
        	else
        		return false;
    	}
    		
    	return true;
    }
	
	public static boolean isMayLucky(LotteryRecord record)
	{
		if(record == null || record.getState()!="1")
			return true;
		
		return false;
	}
	
	public static Prize setThePrize(Prize prize, int prizeType, String snCode)
	{	 
		prize.setSn(snCode);
		prize.setSuccess(true);
		prize.setPrizetype(prizeType);
		prize.setState("2");
		
		return prize;
	}
	
}
