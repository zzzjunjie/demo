package com.example.demo.player.dto;


import lombok.Data;


/**
 * 添加经验请求
 *
 * @author zhoujunjie
 */
@Data
public class AddPlayerExperienceReq {

	/**
	 * 玩家ID
	 */
	private int id;

	/**
	 * 新增经验值
	 */
	private int addValue;

}
