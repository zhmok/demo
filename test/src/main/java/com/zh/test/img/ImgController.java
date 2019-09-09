package com.zh.test.img;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1")
public class ImgController {


    /**
     * 方式一
     * 使用response 返回图片
     * @param response
     * @throws IOException
     */
    @GetMapping("/img")
    public void getImg(HttpServletResponse response) throws IOException {
        // 设置放回类型
        response.setContentType("image/png");
        BufferedImage img = ImageUtils.getImg("");
        ImageIO.write(img,"JPG",response.getOutputStream());
    }

    /**
     * 如果前段无法直接接收图片
     * 则将图片转码成 16进制 字符串放回
     * 使用 Base64
     * @throws IOException
     */
    @GetMapping("/img")
    public String getImg() throws IOException {
        BufferedImage img = ImageUtils.getImg("");

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ImageIO.write(img,"JPG",b);

        return Base64.getEncoder().encodeToString(b.toByteArray());
    }

}
