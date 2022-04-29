package com.example.demo.player.request;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * 获取玩家信息协议
 *
 * @author zhoujunjie
 */
@Getter
@Setter
public class GetPlayersReq {

	/**
	 * 玩家ID列表 如果为null则获取所有玩家
	 */
	private List<Integer> ids;

}
