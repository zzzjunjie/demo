package com.example.demo.player.controller;


import com.example.demo.player.dto.AddPlayerExperienceReq;
import com.example.demo.player.dto.GetPlayersReq;
import com.example.demo.player.entity.Player;
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
@RequestMapping("/player")
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
	@PostMapping("/getPlayers")
	public String getPlayers(@RequestBody GetPlayersReq req) {
		List<Player> players = playerService.getPlayers(req.getIds());
		return ResultUtils.successWithData(players);
	}

	/**
	 * 添加玩家经验
	 *
	 * @param req 请求协议
	 */
	@PostMapping("/addPlayerExperience")
	public String addPlayerExperience(@RequestBody AddPlayerExperienceReq req) {
		boolean b = playerService.addPlayerExperience(req.getId(), req.getAddValue());
		if (b) {
			return ResultUtils.successWithData("添加经验成功");
		} else {
			return ResultUtils.errorWithData("添加经验失败");
		}
	}

	@GetMapping("/getAll")
	public String getAll() {
		List<Player> all = playerService.getAll();
		return ResultUtils.successWithData(all);
	}

}
