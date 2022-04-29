package com.example.demo.player.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 玩家基础信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

	/**
	 * 玩家ID
	 */
	private int id;

	/**
	 * 玩家名称
	 */
	private String name;

}