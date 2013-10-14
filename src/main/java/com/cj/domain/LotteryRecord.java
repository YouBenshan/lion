package com.cj.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class LotteryRecord
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//活动ID
	private Long lotteryID;
	
	//参加活动微信ID
	private String wechatId;
	
	//中奖状态
	private String state;
	
	//当天参加的次数
	private int dayNum;
	
	//参加活动的总次数
	private int totalNum;
	
	//中奖的序列号
	private String sn;
	
	//最近参加活动时间
	private Date lastDateTime;
    
	//兑奖手机号码
	private long phone;
	
	//真实姓名
	private String userName;
	
	//中奖项目的ID
	private int prizeItemId;
	
	//是否兑换奖品
	private int cashPrize;
	
	//奖品是否发放
	private int sendPrize;
}
