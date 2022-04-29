package com.example.demo.player.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 添加经验请求
 *
 * @author zhoujunjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPlayerExperienceReq {

	/**
	 * 玩家ID
	 */
	private int id;

	/**
	 * 新增经验值
	 */
	private long addValue;

}
