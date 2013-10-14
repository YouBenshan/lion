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
public class LotterySetUp 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//主键ID，自动增长
	
	//活动ID
	private Long lotteryID;
	
	//活动的名称
	private String lotteryName;
	
	//活动开始时间
	private Date beginTime;
	
	//活动结束时间
	private Date endTime;
	
	//兑奖截止时间
	private Date lotteryExp;
	
	//每天的最大发放奖品数量
	private int dayMaxPriz;
	
	//按照参加抽奖人数每达到该数量发放该奖一份
	private int payPersonNum;
	
	//按照天数(每天)发放该奖品的总份数
	private int payDayNum;
	
	//估计当天参加抽奖人数
	private int estimateNum;
	
	//估计变化人数（增加或者减少）
	private int changeNum;
	
	//按照人发放奖品开关
	private boolean payPersonState;
	
	//按照天发放奖品开关
	private boolean payDayState;
	
	//每人参加该活动的最大次数
	private int maxNum;
	
	//每天每天参加该活动的最大次数
	private int maxNumDay;
	 
	//指定中奖用户
	private String wechatId;
	
	//是否允许同一用户多次中奖
	private boolean allowSameUser;
	
	//该活动状态
	private String state;	
}
