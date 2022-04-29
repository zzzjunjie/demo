package com.example.demo.player.utils;


/**
 * Id生成工厂
 */
public class IdFactory {

	private static int ID = 0;

	public synchronized static int getId() {
		int id = ID++;
		while ((id & 1) == 0) {
			return id;
		}
		return 0;
	}

}
