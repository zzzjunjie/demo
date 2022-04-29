package com.example.demo.player.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.player.constant.RequestPathConst;
import com.example.demo.player.entity.Player;
import com.example.demo.player.request.AddPlayerExperienceReq;
import com.example.demo.player.service.IPlayerServerBridging;
import com.example.demo.player.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


/**
 * 对接玩家系统
 *
 * @author zhoujunjie
 */
@Slf4j
@Service
public class PlayerServerBridgingImpl implements IPlayerServerBridging {

	private final String URL = "http://localhost:";

	@Value("${server.port:8083}")
	public String port;

	@Resource
	private RestTemplate restTemplate;

	/**
	 * 获取玩家信息
	 *
	 * @param id 玩家ID
	 * @return 玩家信息
	 */
	@Override
	public Player getPlayerById(int id) {
		Map<String, Integer> reqParam = new HashMap<>();
		reqParam.put("id", id);
		ResponseEntity<String> playerResponseEntity = this.restTemplate.getForEntity(URL + port + RequestPathConst.PlayerController.GET_PLAYERS, String.class, reqParam);
		if (!playerResponseEntity.getStatusCode().is2xxSuccessful()) {
			log.error("请求获取玩家数据异常异常,玩家ID:[{}],异常消息:[{}]", id, playerResponseEntity.getBody());
			return null;
		}
		String body = playerResponseEntity.getBody();
		if (StringUtils.isBlank(body)) {
			log.error("请求获取玩家数据返回信息为空,玩家ID:[{}]", id);
			return null;
		}
		Map map = JSONObject.parseObject(body, Map.class);
		String code = (String) map.get(ResultUtils.CODE);
		if (code.equals(ResultUtils.SUCCESS_CODE)) {
			String data = (String) map.get(ResultUtils.DATA);
			if (StringUtils.isNoneBlank(data)) {
				return JSONObject.parseObject(data, Player.class);
			}
		}
		log.error("请求获取玩家数据异常,玩家ID:[{}],返回状态码:[{}],返回数据:[{}]", id, code, body);
		return null;
	}

	/**
	 * 添加玩家
	 *
	 * @param id       玩家ID
	 * @param addValue 添加的经验
	 * @return 添加结果回调
	 */
	@Override
	public CompletableFuture<Boolean> asyncAddPlayerExperience(int id, int addValue) {
		return CompletableFuture.supplyAsync(() -> addPlayerExperience1(id, addValue));
	}

	private Boolean addPlayerExperience1(int id, int addValue) {
		AddPlayerExperienceReq addPlayerExperienceReq = new AddPlayerExperienceReq(id, addValue);
		ResponseEntity<String> playerResponseEntity = this.restTemplate.postForEntity(URL + port + RequestPathConst.PlayerController.ADD_PLAYER_EXPERIENCE, addPlayerExperienceReq, String.class);
		if (!playerResponseEntity.getStatusCode().is2xxSuccessful()) {
			log.error("请求新增玩家经验异常,玩家ID:[{}],异常消息:[{}]", id, playerResponseEntity.getBody());
			return false;
		}
		String body = playerResponseEntity.getBody();
		if (StringUtils.isBlank(body)) {
			log.error("请求新增玩家经验响应数据为空,玩家ID:[{}]", id);
			return null;
		}
		Map map = JSONObject.parseObject(body, Map.class);
		String code = (String) map.get(ResultUtils.CODE);
		return code.equals(ResultUtils.SUCCESS_CODE);
	}

}
