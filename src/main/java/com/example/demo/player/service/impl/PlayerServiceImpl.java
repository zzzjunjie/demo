package com.example.demo.player.service.impl;


import com.example.demo.player.entity.Player;
import com.example.demo.player.exception.BusinessException;
import com.example.demo.player.service.IPlayerService;
import com.example.demo.player.utils.IdFactory;
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
public class PlayerServiceImpl implements IPlayerService {

	/**
	 * 玩家列表缓存
	 * K:玩家ID V:玩家信息
	 */
	private final Map<Integer, Player> playerMap = new ConcurrentHashMap<>();

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
	 * @param name 玩家名称
	 * @return TRUE:添加成功
	 */
	boolean addPlayer(String name) {
		Player player = new Player(IdFactory.getId(), name);
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
			throw new BusinessException(String.format("添加经验值不能小于0,玩家ID:%s,添加经验值:%s", id, addValue));
		}
		Player player = playerMap.get(id);
		if (player == null) {
			throw new BusinessException(String.format("玩家信息不存在,玩家ID:%s", id));
		}
		long newExperience = player.getExperience() + addValue;
		player.setExperience(newExperience);
		return true;
	}

}
