package com.example.demo.player.constant;


/**
 * 请求路径常量
 *
 * @author zhoujunjie
 */
public interface RequestPathConst {

	interface PlayerController {

		/**
		 * 根路径
		 */
		String PATH = "/player";

		/**
		 * 获取玩家列表协议路径
		 */
		String GET_PLAYERS = PATH + "/getPlayers";

		/**
		 * 添加玩家经验协议路径
		 */
		String ADD_PLAYER_EXPERIENCE = PATH + "/addPlayerExperience";

		/**
		 * 获取单个玩家协议路径
		 */
		String GET_PLAYER = PATH + "/getPlayer";

	}

}
