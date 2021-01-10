package com.piter.provider1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 服务提供者
 */
//@SpringCloudApplication
@SpringBootApplication
@EnableDiscoveryClient
public class Provider1Application {

	public static void main(String[] args) {
		SpringApplication.run(Provider1Application.class, args);
	}

}

