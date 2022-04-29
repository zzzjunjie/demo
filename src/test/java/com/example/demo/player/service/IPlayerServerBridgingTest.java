package com.example.demo.player.service;


import com.example.demo.player.constant.RequestPathConst;
import com.example.demo.player.constant.ResultConst;
import com.example.demo.player.entity.Player;
import com.example.demo.player.service.impl.PlayerServerBridgingImpl;
import com.example.demo.player.utils.ResultUtils;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@SpringBootTest
class IPlayerServerBridgingTest {

	@Value("${server.port:8083}")
	public String PORT;

	private String URL = "http://localhost:";

	@Spy
	private PlayerServerBridgingImpl playerServerBridging;

	@Spy
	private RestTemplate restTemplate;

	@BeforeEach
	public void setUp() {
		// mock对系统接口
		MockitoAnnotations.openMocks(this);
		Player player = new Player(1, "test");
		// 调用外部系统直接返回成功,不受外部业务影响
		ResponseEntity<String> getPlayerRes = new ResponseEntity<>(ResultUtils.successWithData(player), HttpStatus.OK);
		Mockito.when(restTemplate.getForEntity(URL + PORT + RequestPathConst.PlayerController.GET_PLAYERS, String.class, Mockito.anyString())).thenReturn(getPlayerRes);
		// 替换POST请求
		String addPlayerExperienceBody = ResultUtils.successWithData(ResultConst.ADD_SUCCESS);
		ResponseEntity<String> addPlayerExperienceRes = new ResponseEntity<>(ResultUtils.successWithData(addPlayerExperienceBody), HttpStatus.OK);
		Mockito.when(restTemplate.postForEntity(URL + PORT + RequestPathConst.PlayerController.ADD_PLAYER_EXPERIENCE, Mockito.any(), String.class)).thenReturn(addPlayerExperienceRes);
	}

	@DisplayName("测试是否为mock协议返回")
	@Test
	void getPlayerById_With_Equals() {
		Player player = new Player(1, "test");
		Player playerById = playerServerBridging.getPlayerById(1);
		Assertions.assertEquals(playerById, player);
	}

	@Test
	void addPlayer() {
		CompletableFuture<Boolean> booleanCompletableFuture = playerServerBridging.asyncAddPlayerExperience(1, 10);
		Awaitility.await().until(() -> {
			try {
				Assertions.assertTrue(booleanCompletableFuture.get());
			} catch (InterruptedException | ExecutionException e) {
				Assertions.fail(String.format("测试异常,消息:%s", e.getMessage()));
			}
		});
	}

}