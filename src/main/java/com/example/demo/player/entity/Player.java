package com.example.demo.player.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


/**
 * 玩家基础信息
 *
 * @author zhoujunjie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

	/**
	 * 玩家ID
	 */
	private int id;

	/**
	 * 玩家名称
	 */
	private String name;

	/**
	 * 玩家经验
	 */
	private long experience;

	public Player(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Player player = (Player) o;
		return id == player.id && experience == player.experience && Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, experience);
	}

}