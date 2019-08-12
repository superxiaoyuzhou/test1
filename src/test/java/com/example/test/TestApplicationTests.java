package com.example.test;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.response.AlipayOfflineMaterialImageUploadResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

	@Test
	public void test1() {
		List<String> list = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
		list.stream().forEach(
				str -> {
					if ("bc".equals(str)){
						return;
					}
					System.out.println(str);
				}
		);
		System.out.println("xxxxxxxxxxxxxxxxx");
	}


	@Test
	public void test2(){
		Optional<Object> empty = Optional.empty();
		System.out.println(empty.isPresent());
		System.out.println(empty.get());
	}
	@Test
	public void test3(){
		//支付宝加解密
//		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
//		AlipayOfflineMaterialImageUploadResponse response = alipayClient.execute(request);
	}

	@Test
	public void test4(){

		for (int i = 1 ;i <= 128;i++){
			System.out.print("一");
		}


	}

	@Test
	public void test5(){
		BigDecimal bigDecimal = new BigDecimal(-10)
		.multiply(new BigDecimal(-1));
		System.out.println(bigDecimal);

	}

}

