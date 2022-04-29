package com.example.demo.player.controller;


import com.example.demo.player.constant.RequestPathConst;
import com.example.demo.player.constant.ResultConst;
import com.example.demo.player.entity.Player;
import com.example.demo.player.request.AddPlayerExperienceReq;
import com.example.demo.player.request.GetPlayersReq;
import com.example.demo.player.service.IPlayerService;
import com.example.demo.player.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 玩家操作控制器API
 *
 * @author zhoujunjie
 */
@RestController
@Controller
public class PlayerController {

	@Autowired
	private IPlayerService playerService;

	/**
	 * 获取玩家列表
	 *
	 * @param req 请求协议
	 * @return 玩家列表数据
	 */
	@PostMapping(RequestPathConst.PlayerController.GET_PLAYERS)
	public String getPlayers(@RequestBody GetPlayersReq req) {
		List<Player> players = playerService.getPlayers(req.getIds());
		return ResultUtils.successWithData(players);
	}

	/**
	 * 添加玩家经验
	 *
	 * @param req 请求协议
	 */
	@PostMapping(RequestPathConst.PlayerController.ADD_PLAYER_EXPERIENCE)
	public String addPlayerExperience(@RequestBody AddPlayerExperienceReq req) {
		boolean b = playerService.addPlayerExperience(req.getId(), req.getAddValue());
		if (b) {
			return ResultUtils.successWithData(ResultConst.ADD_SUCCESS);
		} else {
			return ResultUtils.errorWithData(ResultConst.ERROR_SUCCESS);
		}
	}

	/**
	 * 根据玩家ID获取玩家信息
	 *
	 * @param id 玩家ID
	 * @return 玩家信息
	 */
	@RequestMapping(RequestPathConst.PlayerController.GET_PLAYER)
	public String getPlayer(@RequestParam int id) {
		Player player = playerService.getPlayer(id);
		return ResultUtils.successWithData(player);
	}

}
