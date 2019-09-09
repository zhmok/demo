package com.zh.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest()
//@ContextConfiguration(classes = AuthorizationApplication.class)
//@AutoConfigureMockMvc
public class AuthorizationCodeLoginTest {

//    @Autowired
//    public WebApplicationContext wac;


    @Autowired
    public MockMvc mvc;

    @Test
    public void login() throws Exception {

        this.mvc.perform(get(" /authorize")).andDo(print());
    }





//    @Test
    public void test() throws Exception {
        System.out.println(mvc);
        this.mvc.perform(get("/hello"))
                .andDo(print())
//                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();
//                .andExpect(status().isOk());

    }




}
