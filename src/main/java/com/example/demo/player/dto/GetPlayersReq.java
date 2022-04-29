package com.example.demo.player.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
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
	 * 玩家ID列表
	 */
	@NotEmpty
	private List<Integer> ids;

}
