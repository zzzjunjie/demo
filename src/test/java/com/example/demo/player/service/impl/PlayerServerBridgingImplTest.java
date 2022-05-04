package com.example.demo.player.service.impl;


import com.example.demo.player.constant.RequestPathConst;
import com.example.demo.player.constant.ResultConst;
import com.example.demo.player.entity.Player;
import com.example.demo.player.request.AddPlayerExperienceReq;
import com.example.demo.player.utils.ResultUtils;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * 玩家系统对接测试类
 *
 * @author zhoujunjie
 */
@DisplayName("测试对接系统")
@SpringBootTest
class PlayerServerBridgingImplTest {

	@Autowired
	private PlayerServerBridgingImpl playerServerBridging;

	@Mock
	private RestTemplate restTemplate;

	/**
	 * 测试数据
	 */
	private Player textPlayer = new Player(1, "test");

	/**
	 * 测试获取查询玩家API
	 */
	private ResponseEntity<String> withEqualTestRes = new ResponseEntity<>(ResultUtils.successWithData(textPlayer), HttpStatus.OK);

	/**
	 * 测试获取查询玩家API
	 */
	private ResponseEntity<String> withNotEqualTestRes = new ResponseEntity<>(ResultUtils.successWithData(null), HttpStatus.OK);

	/**
	 * 玩家添加经验协议参数模拟
	 */
	private AddPlayerExperienceReq addPlayerExperienceReq = new AddPlayerExperienceReq(1, 10);

	/**
	 * 获取玩家信息参数模拟
	 */
	private Map<String, Integer> withEqualTestParam = Map.of("id", 1);

	/**
	 * 获取玩家信息参数模拟
	 */
	private Map<String, Integer> withNotEqualTestParam = Map.of("id", 1000);

	@BeforeEach
	public void setUp() {
		// 开启mock
		MockitoAnnotations.openMocks(this);
		// mock RestTemplate
		mockRestTemplate();
		// 替换restTemplate
		playerServerBridging.setRestTemplate(restTemplate);
	}

	/**
	 * mock RestTemplate 避免直接请求三方系统的接口
	 */
	private void mockRestTemplate() {
		// 替换GET请求
		Mockito.when(restTemplate.getForEntity(playerServerBridging.address + RequestPathConst.PlayerController.GET_PLAYER, String.class, withEqualTestParam)).thenReturn(withEqualTestRes);
		Mockito.when(restTemplate.getForEntity(playerServerBridging.address + RequestPathConst.PlayerController.GET_PLAYER, String.class, withNotEqualTestParam)).thenReturn(withNotEqualTestRes);
		// 替换POST请求
		String addPlayerExperienceBody = ResultUtils.successWithData(ResultConst.ADD_EXPERIENCE_SUCCESS);
		ResponseEntity<String> addPlayerExperienceRes = new ResponseEntity<>(ResultUtils.successWithData(addPlayerExperienceBody), HttpStatus.OK);
		Mockito.when(restTemplate.postForEntity(playerServerBridging.address + RequestPathConst.PlayerController.ADD_PLAYER_EXPERIENCE, addPlayerExperienceReq, String.class))
			.thenReturn(addPlayerExperienceRes);
	}

	@DisplayName("测试根据id是匹配正确的玩家")
	@Test
	void getPlayerById_with_equals() {
		Player playerById = playerServerBridging.getPlayerById(textPlayer.getId());
		Assertions.assertEquals(playerById, textPlayer);
	}

	@DisplayName("测试根据id是匹配非确的玩家")
	@Test
	void getPlayerById_with_not_equals() {
		Player playerById = playerServerBridging.getPlayerById(withNotEqualTestParam.get("id"));
		Assertions.assertNotEquals(playerById, textPlayer);
	}

	@DisplayName("模拟异步添加经验成功的情况")
	@Test
	void asyncAddPlayerExperience_with_success() {
		CompletableFuture<Boolean> booleanCompletableFuture = playerServerBridging.asyncAddPlayerExperience(addPlayerExperienceReq.getId(), addPlayerExperienceReq.getAddValue());
		Awaitility.await().until(() -> {
			try {
				Assertions.assertTrue(booleanCompletableFuture.get());
			} catch (InterruptedException | ExecutionException e) {
				Assertions.fail(String.format("测试异常,消息:%s", e.getMessage()));
			}
		});
	}

	@DisplayName("模拟异步添加经验出错的情况")
	@Test
	void asyncAddPlayerExperience_with_error() {
		CompletableFuture<Boolean> booleanCompletableFuture = playerServerBridging.asyncAddPlayerExperience(addPlayerExperienceReq.getId(), addPlayerExperienceReq.getAddValue());
		Awaitility.await().until(() -> {
			try {
				Assertions.assertTrue(booleanCompletableFuture.get());
			} catch (InterruptedException | ExecutionException e) {
				Assertions.fail(String.format("测试异常,消息:%s", e.getMessage()));
			}
		});
	}

}