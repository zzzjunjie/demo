package com.example.demo.player.handle;


import com.example.demo.player.exception.BusinessException;
import com.example.demo.player.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局拦截异常处理器
 *
 * @author zhoujunjie
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandle {

	/**
	 * 业务异常拦截
	 *
	 * @param ex 异常消息
	 */
	@ExceptionHandler({ BusinessException.class })
	@ResponseBody
	public String requestNotReadable(BusinessException ex) {
		log.error("业务异常:[{}]", ex.getMessage());
		return ResultUtils.errorWithData(ex.getMessage());
	}

}
