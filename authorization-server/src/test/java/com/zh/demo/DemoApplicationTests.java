package com.zh.demo;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AuthorizationApplication.class)
public class DemoApplicationTests {


	@Rollback
	@Test
	public void contextLoads() {
		System.out.println(1);
	}

}
