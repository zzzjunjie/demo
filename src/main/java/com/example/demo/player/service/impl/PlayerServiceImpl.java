package com.example.demo.player.service.impl;


import com.example.demo.player.constant.ResultConst;
import com.example.demo.player.entity.Player;
import com.example.demo.player.exception.BusinessException;
import com.example.demo.player.service.IPlayerService;
import com.example.demo.player.utils.IdFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 玩家操作实现
 *
 * @author zhoujunjie
 */
@Service
public class PlayerServiceImpl implements IPlayerService, InitializingBean {

	/**
	 * 玩家列表缓存
	 * K:玩家ID V:玩家信息
	 */
	private final Map<Integer, Player> playerMap = new ConcurrentHashMap<>();

	@Override
	public void afterPropertiesSet() {
		// 初始化玩家数据
		int playerNum = 20;
		for (int i = 0; i < playerNum; i++) {
			String nameFormat = "name-%s";
			Player player = new Player(IdFactory.getId(), String.format(nameFormat, i));
			playerMap.put(player.getId(), player);
		}
	}

	/**
	 * 根据玩家ID获取玩家信息
	 *
	 * @param id 玩家ID
	 * @return 玩家信息
	 */
	@Override
	public Player getPlayer(int id) {
		return playerMap.get(id);
	}

	/**
	 * 获取玩家信息列表
	 *
	 * @param ids 玩家ID列表
	 * @return 玩家信息列表
	 */
	@Override
	public List<Player> getPlayers(List<Integer> ids) {
		List<Player> players = new ArrayList<>();
		for (Integer id : ids) {
			Player player = playerMap.get(id);
			if (player != null) {
				players.add(player);
			}
		}
		return players;
	}

	/**
	 * 添加玩家
	 *
	 * @param player 玩家
	 * @return TRUE:添加成功
	 */
	boolean addPlayer(Player player) {
		return playerMap.putIfAbsent(player.getId(), player) == null;
	}

	/**
	 * 添加玩家经验
	 *
	 * @param id       玩家ID
	 * @param addValue 经验新增值
	 * @return TRUE:新增成功 FALSE:新增失败
	 */
	@Override
	public boolean addPlayerExperience(int id, long addValue) {
		if (addValue <= 0) {
			throw new BusinessException(String.format(ResultConst.ADD_EXPERIENCE_EXCEPTION_WITH_VALUE_LT_ZERO, id, addValue));
		}
		Player player = playerMap.get(id);
		if (player == null) {
			throw new BusinessException(String.format(ResultConst.PLAYER_NOT_EXIT, id));
		}
		long newExperience = player.getExperience() + addValue;
		player.setExperience(newExperience);
		return true;
	}

}
