package com.example.demo.player.exception;


/**
 * 自定义业务异常处理器
 *
 * @author zhoujunjie
 */
public class BusinessException extends RuntimeException {

	public BusinessException(String message) {
		super(message);
	}

}
