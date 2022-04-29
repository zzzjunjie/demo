package com.example.demo.player.utils;


/**
 * Id生成工厂
 */
public class IdFactory {

	private static int ID = 0;

	public synchronized static int getId() {
		while (true) {
			int id = ID++;
			if ((id & 1) != 0) {
				return id;
			}
		}
	}

}
