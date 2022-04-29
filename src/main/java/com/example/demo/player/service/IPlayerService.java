package com.example.demo.player.service;


import com.example.demo.player.entity.Player;


/**
 * 玩家操作接口
 */
public interface IPlayerService {

	/**
	 * 根据玩家ID获取玩家信息
	 *
	 * @param id 玩家ID
	 * @return 玩家信息
	 */
	Player getPlayer(int id);

}