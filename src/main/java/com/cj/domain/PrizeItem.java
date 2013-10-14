package com.cj.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class PrizeItem 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//主键ID，自动增长
	
	//活动ID
	private Long lotteryID;
	
	//活动的奖项
	private int itemId;
	
	//活动奖项描述
	private String itemDesc;
	
	//活动的奖品设置数量
	private int prizeNum;
	
	//奖品剩余数量
	private int remainNum;
}
