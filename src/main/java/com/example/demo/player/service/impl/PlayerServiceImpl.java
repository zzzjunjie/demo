package com.example.demo.player.service.impl;


import com.example.demo.player.entity.Player;
import com.example.demo.player.exception.BusinessException;
import com.example.demo.player.service.IPlayerService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
	 * 创建玩家名称
	 */
	private final String NAME_FORMAT = "Hello-%s";

	/**
	 * 默认玩家个数
	 */
	private final int playerNum = 20;

	/**
	 * 玩家列表缓存
	 * K:玩家ID V:玩家信息
	 */
	private final Map<Integer, Player> playerMap = new ConcurrentHashMap<>();

	/**
	 * 初始化玩家缓存列表
	 */
	@PostConstruct
	public void initPlayerMap() {
		for (int i = 0; i < playerNum; i++) {
			Player player = new Player(i, String.format(NAME_FORMAT, i));
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
	 * 添加玩家经验
	 *
	 * @param id       玩家ID
	 * @param addValue 经验新增值
	 * @return TRUE:新增成功 FALSE:新增失败
	 */
	@Override
	public boolean addPlayerExperience(int id, int addValue) {
		if (addValue <= 0) {
			throw new BusinessException(String.format("添加经验值不能小于0,玩家ID:%s,添加经验值:%s", id, addValue));
		}
		Player player = playerMap.get(id);
		if (player == null) {
			throw new BusinessException(String.format("玩家信息不存在,玩家ID:%s", id));
		}
		int newExperience = player.getExperience() + addValue;
		player.setExperience(newExperience);
		return true;
	}

	/**
	 * 获取所有玩家信息
	 *
	 * @return 所有玩家信息列表
	 */
	@Override
	public List<Player> getAll() {
		return new ArrayList<>(playerMap.values());
	}

}
