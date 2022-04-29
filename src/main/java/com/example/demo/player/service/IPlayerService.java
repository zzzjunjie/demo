package com.example.demo.player.service;


import com.example.demo.player.entity.Player;

import java.util.List;


/**
 * 玩家操作接口
 *
 * @author zhoujunjie
 */
public interface IPlayerService {

	/**
	 * 根据玩家ID获取玩家信息
	 *
	 * @param id 玩家ID
	 * @return 玩家信息
	 */
	Player getPlayer(int id);

	/**
	 * 获取玩家信息列表
	 *
	 * @param ids 玩家ID列表
	 * @return 玩家信息列表
	 */
	List<Player> getPlayers(List<Integer> ids);

	/**
	 * 添加玩家经验
	 *
	 * @param id       玩家ID
	 * @param addValue 经验新增值
	 * @return TRUE:新增成功 FALSE:新增失败
	 */
	boolean addPlayerExperience(int id, int addValue);

	/**
	 * 获取所有玩家信息
	 *
	 * @return 所有玩家信息列表
	 */
	List<Player> getAll();
}