package com.example.demo.player.service.impl;


import com.example.demo.player.entity.Player;
import com.example.demo.player.exception.BusinessException;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@SpringBootTest
class PlayerServiceImplTest {

	@Autowired
	private PlayerServiceImpl playerService;

	@BeforeEach
	public void setUp() {
		String name = "Hello-%s";
		// 新增测试数据
		for (int i = 0; i < 20; i++) {
			boolean b = playerService.addPlayer(String.format(name, i));
			Assertions.assertTrue(b);
		}
	}

	@DisplayName("获取不存在的玩家信息")
	@Test
	void getPlayer_with_Null_Value() {
		Player player = playerService.getPlayer(2);
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
		List<Player> players = playerService.getPlayers(Arrays.asList(1, 3));
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
		try {
			playerService.addPlayerExperience(2, 0);
			Assertions.fail("测试ID不存在的情况,测试类异常");
		} catch (Exception e) {
			Assertions.assertTrue(e instanceof BusinessException);
		}
	}

	@DisplayName("测试添加的经验值为负数的情况")
	@Test
	void addPlayerExperience_With_AddValue_Exception() {
		try {
			playerService.addPlayerExperience(1, -10);
			Assertions.fail("测试添加的经验值为负数的情况,测试类异常");
		} catch (Exception e) {
			Assertions.assertTrue(e instanceof BusinessException);
		}
	}

	@DisplayName("成功添加经验值")
	@Test
	void addPlayerExperience_With_Success() {
		boolean b = playerService.addPlayerExperience(1, 10);
		Assertions.assertTrue(b);
	}

}