package com.zh.demo;

import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {

		Map m;


		System.out.println(1);

		Set s= new TreeSet();
		s.add(null);
		s.add("1");


		s.forEach(System.out::print);
		System.out.println(s);




















//		StringBuilder str = new StringBuilder(1);
//
//		for (int i = 0; i < 100; i++) {
//			str.append(i + " ");
//		}
//		String[][] ss=new String[100][100];
//		for (int i = 0; i < 100; i++) {
//			ss[i] = new String[100];
//
//			for (int j = 0; j < 100; j++) {
//				ss[i][j] = j+" ";
//			}
//		}
//
//		Stream.of(ss).flatMap(Arrays::stream).forEach(v->{
//			System.out.print(v);
//			if("99 ".equals(v)){
//				System.out.println();
//			}
//		});
//		Stream.of(ss).forEach(v-> {
//			for (String s : v) {
//				System.out.print(s+"  ");
//			}
//			System.out.println();
//		});



	}

}
