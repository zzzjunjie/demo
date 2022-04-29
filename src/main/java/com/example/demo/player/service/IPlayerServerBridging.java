package com.example.demo.player.service;


import com.example.demo.player.entity.Player;

import java.util.concurrent.CompletableFuture;


/**
 * 玩家系统对接服务
 * 1、获取当个玩家信息
 * 2、添加玩家信息，返回CompletableFuture<Boolean>
 *
 * @author zhoujunjie
 */
public interface IPlayerServerBridging {

	/**
	 * 获取玩家数据
	 *
	 * @param id 玩家ID
	 * @return 玩家数据
	 */
	Player getPlayerById(int id);

	/**
	 * 添加玩家
	 *
	 * @param id       玩家ID
	 * @param addValue 添加的经验
	 * @return 添加结果回调
	 */
	CompletableFuture<Boolean> asyncAddPlayerExperience(int id, int addValue);

}
