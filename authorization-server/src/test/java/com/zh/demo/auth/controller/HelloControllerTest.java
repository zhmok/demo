package com.zh.demo.auth.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

//@RunWith(Parameterized.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloControllerTest {

//    @Parameterized.Parameters
//    public static Collection data(){
//        return Arrays.asList("1","2");
//    }
//    public HelloControllerTest(String s){
//        this.i = s;
//    }
//    private String i;

    @Autowired
    HelloController helloController;

    @Test
    public void get() throws IOException, InterruptedException {
//        System.out.println("i="+i);
        HelloController mock = spy(HelloController.class);
//        mock.objectMapper = Mockito.mock(ObjectMapper.class);
        mock.objectMapper = spy(ObjectMapper.class);
//        when(mock.objectMapper.writeValueAsString(any())).thenThrow(new RuntimeException());
//        when(mock.objectMapper.writeValueAsString(any())).thenReturn("get hello");
        System.out.println(mock.get());
        System.out.println(mock.get());
//        assertEquals("get hello",mock.get());

        verify(mock,timeout(0).times(2)).get();


//        .get();

    }

    @Test
    public void test() throws IOException, InterruptedException {
        //        this.mvc.perform(post("/oauth/token")
//                        .header("Authorization", "Basic bTE6bTE=")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(new ObjectMapper().writeValueAsBytes(params)))
//                .andDo(print())
//                .andExpect(status().isOk()) ;
    }
}