package com.happylife.core;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.happylife.core.mbg.mappers")
public class CoreApplication {

	private final static Logger logger = LoggerFactory.getLogger(CoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);

	}

}
