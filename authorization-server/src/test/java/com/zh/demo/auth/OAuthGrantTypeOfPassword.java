package com.zh.demo.auth;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 这个测试并不属于单元测试
 * 这个个测试需要依赖项目启动后才可以测试
 * 因为技术有限 无法使用 mockmvc 对security 的/oauth/token 接口单元测试
 * 所以使用了resttemplate 模拟http 进行测试
 * 启动测试:
 * 1.注释 {@code #@Ignore} 注解
 * 2.查看是否启动项目
 * 3.查看 {@link #getSimpleToken()} 里 url 是否正确
 * 4.检查 clien id 和 client secret
 * 5.检查请求头 的client id 和client secret 要求base64编码
 * 6.请求完得到结果
 * <pre>
 * {
 *     "access_token": "f57293e0-05d0-49d1-b511-1cffe7005f07",
 *     "token_type": "bearer",
 *     "expires_in": 43199,
 *     "scope": "all"
 * }
 * </pre>
 * 7.拿到结果里的 token_type access_token 中间用空格隔开 组成 bearer f57293e0-05d0-49d1-b511-1cffe7005f07
 * 8.去请求其他接口时 在请求头中添加  Authorization = "bearer  f57293e0-05d0-49d1-b511-1cffe7005f07"
 * 然后就可以愉快的玩耍啦
 */
//@Ignore
public class OAuthGrantTypeOfPassword {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getSimpleToken() throws Exception {
        String url = "http://localhost:8080/oauth/token";
        String clienId = "m1";
        String clientSecret = "m1";
        String grantType = "password";
        String username = "123";
        String password = "123";


        // 请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic "+
                new String(Base64.getEncoder().encode((clienId + ":" + clientSecret).getBytes("utf-8"))));
        // 请求参数
        MultiValueMap params = new LinkedMultiValueMap();
        params.add("grant_type", grantType);
        params.add("username", username);
        params.add("password", password);
        params.add("client_id", clienId);
        params.add("client_secret", clientSecret);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);



        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody().get("access_token"));
    }


}
