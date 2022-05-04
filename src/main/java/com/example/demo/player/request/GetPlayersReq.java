package com.example.demo.player.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


/**
 * 获取玩家信息协议
 *
 * @author zhoujunjie
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPlayersReq {

	/**
	 * 玩家ID列表 如果为null则获取所有玩家
	 */
	private List<Integer> ids;

}
