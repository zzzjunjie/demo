package com.example.demo.player.utils;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;


/**
 * Result结果工具类
 *
 * @author zhoujunjie
 */
public class ResultUtils {

	/**
	 * 状态码Key
	 */
	public final static String CODE = "code";

	/**
	 * 成功状态码
	 */
	public final static String SUCCESS_CODE = "200";

	/**
	 * 失败状态码
	 */
	public final static String ERROR_CODE = "500";

	/**
	 * 成功消息
	 */
	public final static String SUCCESS_MESSAGE = "success";

	/**
	 * 失败消息
	 */
	public final static String ERROR_MESSAGE = "error";

	/**
	 * 数据Key
	 */
	public final static String DATA = "data";

	/**
	 * 消息Key
	 */
	public final static String MESSAGE = "message";

	/**
	 * 返回成功的结果
	 *
	 * @param data 数据
	 * @return 成功字符串
	 */
	public static String successWithData(Object data) {
		Map<Object, Object> map = new HashMap<>();
		map.put(CODE, SUCCESS_CODE);
		map.put(MESSAGE, SUCCESS_MESSAGE);
		map.put(DATA, data);
		return JSON.toJSONString(map);
	}

	/**
	 * 返回失败结果
	 *
	 * @param data 数据
	 * @return 失败结果字符串
	 */
	public static String errorWithData(String data) {
		Map<Object, Object> map = new HashMap<>();
		map.put(CODE, ERROR_CODE);
		map.put(MESSAGE, ERROR_MESSAGE);
		map.put(DATA, data);
		return JSON.toJSONString(map);
	}

}
