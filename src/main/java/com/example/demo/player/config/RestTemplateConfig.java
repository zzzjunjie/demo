package com.example.demo.player.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;


/**
 * RestTemplate注入Bean
 *
 * @author zhoujunjie
 */
@Configuration
public class RestTemplateConfig {

	/**
	 * RestTemplate Bean注册
	 *
	 * @param factory 客户端请求工厂
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return restTemplate;
	}

	/**
	 * ClientHttpRequestFactory Bean注册
	 *
	 * @return ClientHttpRequestFactory Bean
	 */
	@Bean
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		// 读取超时时间
		simpleClientHttpRequestFactory.setReadTimeout(5000);
		// 连接超时时间
		simpleClientHttpRequestFactory.setConnectTimeout(5000);
		return simpleClientHttpRequestFactory;
	}

}
