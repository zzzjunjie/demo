package com.example.demo.player.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.player.constant.ResultConst;
import com.example.demo.player.entity.Player;
import com.example.demo.player.exception.BusinessException;
import com.example.demo.player.request.AddPlayerExperienceReq;
import com.example.demo.player.request.GetPlayersReq;
import com.example.demo.player.utils.ResultUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 玩家系统API请求测试类
 *
 * @author zhoujunjie
 */
@SpringBootTest
class PlayerControllerTest {

	@Autowired
	private PlayerController playerController;

	@DisplayName("获取id为偶数的玩家数据")
	@Test
	void getPlayer_with_even_Id() {
		String res = playerController.getPlayer(2);
		Map map = JSONObject.parseObject(res, Map.class);
		Assertions.assertNull(map.get(ResultUtils.DATA));
	}

	@DisplayName("获取id为奇数的玩家")
	@Test
	void getPlayer_with_not_old_num_value() {
		int playerId = 1;
		String res = playerController.getPlayer(playerId);
		Map map = JSONObject.parseObject(res, Map.class);
		JSONObject jsonObject = (JSONObject) map.get(ResultUtils.DATA);
		Player player = jsonObject.toJavaObject(Player.class);
		Assertions.assertEquals(player.getId(), playerId);
	}

	@DisplayName("获取玩家列表数据")
	@Test
	void getPlayers_with_list() {
		List<Integer> playerIds = Arrays.asList(1, 2, 3, 4);
		GetPlayersReq getPlayersReq = new GetPlayersReq(playerIds);
		String res = playerController.getPlayers(getPlayersReq);
		Map map = JSONObject.parseObject(res, Map.class);
		JSONArray jsonArray = (JSONArray) map.get(ResultUtils.DATA);
		for (Player player : jsonArray.toJavaList(Player.class)) {
			Assertions.assertTrue(playerIds.contains(player.getId()));
		}
	}

	@DisplayName("ID不存在添加经验")
	@Test
	void addPlayerExperience_with_id_exception() {
		AddPlayerExperienceReq req = new AddPlayerExperienceReq(2, 10);
		try {
			playerController.addPlayerExperience(req);
			Assertions.fail("测试异常,输入异常参数未抛异常消息!");
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				Assertions.assertEquals(e.getMessage(), String.format(ResultConst.PLAYER_NOT_EXIT, req.getId()));
			} else {
				Assertions.fail("业务抛出异常类型不为BusinessException");
			}
		}
	}

	@DisplayName("添加经验值为负数")
	@Test
	void addPlayerExperience_with_addValue_exception() {
		AddPlayerExperienceReq req = new AddPlayerExperienceReq(1, -10);
		try {
			playerController.addPlayerExperience(req);
			Assertions.fail("测试异常,输入异常参数未抛异常消息!");
		} catch (Exception e) {
			if (e instanceof BusinessException) {
				Assertions.assertEquals(e.getMessage(), String.format(ResultConst.ADD_EXPERIENCE_EXCEPTION_WITH_VALUE_LT_ZERO, req.getId(), req.getAddValue()));
			} else {
				Assertions.fail("业务抛出异常类型不为BusinessException");
			}
		}
	}

	@DisplayName("成功添加经验值")
	@Test
	void addPlayerExperience_with_success() {
		AddPlayerExperienceReq req = new AddPlayerExperienceReq(1, 10);
		String res = playerController.addPlayerExperience(req);
		Map map = JSONObject.parseObject(res, Map.class);
		String code = (String) map.get(ResultUtils.CODE);
		Assertions.assertEquals(code, ResultUtils.SUCCESS_CODE);
		String data = (String) map.get(ResultUtils.DATA);
		Assertions.assertEquals(data, ResultConst.ADD_EXPERIENCE_SUCCESS);
	}

}