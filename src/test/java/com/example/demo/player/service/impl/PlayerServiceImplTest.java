package com.example.demo.player.service.impl;


import com.example.demo.player.entity.Player;
import com.example.demo.player.service.IPlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class PlayerServiceImplTest {

	private IPlayerService playerService = new PlayerServiceImpl();

	@DisplayName("获取不存在的玩家信息")
	@Test
	void getPlayer_with_Null_Value() {
		Player player = playerService.getPlayer(10000);
		Assertions.assertNull(player);
	}

	@DisplayName("获取存在的玩家信息")
	@Test
	void getPlayer_with_Not_Null_Value() {
		Player player = playerService.getPlayer(1);
		Assertions.assertNotNull(player);
	}

	@DisplayName("获取两个玩家数据")
	@Test
	void getPlayers_With_Two() {
		List<Player> players = playerService.getPlayers(Arrays.asList(1, 2));
		Assertions.assertEquals(2, players.size());
	}

	@DisplayName("获取两个玩家数据,且其中一个不存在")
	@Test
	void getPlayers_With_Two_And_One_Not_Exit() {
		List<Player> players = playerService.getPlayers(Arrays.asList(1, 10000));
		Assertions.assertEquals(1, players.size());
	}

	@DisplayName("测试ID不存在的情况")
	@Test
	void addPlayerExperience_With_Id_Exception() {
		boolean b = playerService.addPlayerExperience(10000, 0);
	}

	@DisplayName("测试添加的经验值为负数的情况")
	@Test
	void addPlayerExperience_With_AddValue_Exception() {
		boolean b = playerService.addPlayerExperience(1, -10);
	}

	@DisplayName("成功添加经验值")
	@Test
	void addPlayerExperience_With_Success() {
		boolean b = playerService.addPlayerExperience(1, 10);
		Assertions.assertTrue(b);
	}

}