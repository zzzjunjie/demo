package com.example.demo.player.service.impl;


import com.example.demo.player.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class PlayerServiceImplTest {

	PlayerServiceImpl playerService = new PlayerServiceImpl();

	@Test
	public void testUp() {
		Player player = playerService.getPlayer(1);
		Assertions.assertEquals(player, new Player(1, "hello"));
	}

}