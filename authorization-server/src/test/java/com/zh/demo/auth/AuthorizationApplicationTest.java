package com.zh.demo.auth;

import com.zh.demo.auth.controller.HelloController;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationApplicationTest {
    @Autowired
    HelloController helloController;



    @Resource
    MockMvc mockMvc;

    @Test
    public void authorizationCode() throws Exception {
        LinkedMultiValueMap params = new LinkedMultiValueMap();
        params.add("response_type","token");
        params.add("client_id","my_client");
        params.add("client_secret","my_scret");
        System.out.println("mockMvc = " + mockMvc);

        String contentAsString = mockMvc.perform(get("/authorize")
                .accept(MediaType.APPLICATION_JSON_UTF8).content(new ObjectMapper().writeValueAsBytes(params))

                .params(params)
        )

                .andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println("contentAsString = " + contentAsString);
//        String s = helloController.get();
//        System.out.println(s);

    }

//    @Ignore
    @Test
    public void login() throws Exception {
        LinkedMultiValueMap params = new LinkedMultiValueMap();
        params.add("grant_type","password");
        params.add("username","123");
        params.add("password","123");
        params.add("client_id","my_client");
        params.add("client_secret","my_scret");
        System.out.println("mockMvc = " + mockMvc);

        String contentAsString = mockMvc.perform(get("/oauth/token")
                .accept(MediaType.APPLICATION_JSON_UTF8).content(new ObjectMapper().writeValueAsBytes(params))

                .params(params)
        )

                .andDo(print()).andReturn().getResponse().getContentAsString();
        System.out.println("contentAsString = " + contentAsString);
//        String s = helloController.get();
//        System.out.println(s);

    }
}