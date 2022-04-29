package com.example.demo.player.controller;


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
 * @author Administrator
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
	 * @param playersReq 玩家id
	 * @return 玩家列表数据
	 */
	@PostMapping("/getPlayers")
	public String getPlayers(@RequestBody GetPlayersReq playersReq) {
		List<Player> players = playerService.getPlayers(playersReq.getIds());
		return ResultUtils.successWithData(players);
	}

	/**
	 * 添加玩家经验
	 *
	 * @param id       玩家ID
	 * @param addValue 经验新增值
	 */
	@PostMapping("/addPlayerExperience")
	public String addPlayerExperience(@RequestParam int id, @RequestParam int addValue) {
		boolean b = playerService.addPlayerExperience(id, addValue);
		if (b) {
			return ResultUtils.successWithData("添加经验成功");
		} else {
			return ResultUtils.errorWithData("添加经验失败");
		}
	}

}
