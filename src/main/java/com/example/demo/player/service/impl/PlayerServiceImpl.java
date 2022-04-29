package com.example.demo.player.service.impl;


import com.example.demo.player.entity.Player;
import com.example.demo.player.service.IPlayerService;
import org.springframework.stereotype.Service;


/**
 * 玩家操作实现
 */
@Service
public class PlayerServiceImpl implements IPlayerService {

	/**
	 * 根据玩家ID获取玩家信息
	 *
	 * @param id 玩家ID
	 * @return 玩家信息
	 */
	@Override
	public Player getPlayer(int id) {
		// TODO 模拟问题修复
		return new Player(id, "hello world");
	}

}
