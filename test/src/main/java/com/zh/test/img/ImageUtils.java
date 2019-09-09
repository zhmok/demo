package com.zh.test.img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageUtils {


    // 宽度
    private static final int WIDTH = 1920;
    // 高度
    private static final int HEIGHT = 1080;
    // 字体大小
    private static final int FONT_SIZE = 24;

    static String imgPath = "C:\\Users\\admi\\Desktop\\";

    public static BufferedImage getImg(String oldImgPath) throws IOException {
//        BufferedImage image = new BufferedImage(
//                WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        BufferedImage image = null;
        if (Files.exists(Paths.get(oldImgPath))) {
            ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(oldImgPath)));
//        ByteArrayInputStream bis = new ByteArrayInputStream(Files.readAllBytes(Paths.get(imgPath )));
            image = ImageIO.read(bis);
        }

//        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
//        int w = image.getWidth();
//        int h = image.getHeight();
        BufferedImage output = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setBackground(Color.cyan);
        // 边框
//        g2.setColor(Color.WHITE);g2.fill(new Line2D.Double(0,0,w,h));
//        int cornerRadius = -45;
//        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        if(image != null){
            g2.drawImage(image, null, 0, 0);
        }
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//        g2.setComposite(AlphaComposite.Src);
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(Color.WHITE);
//        g2.setStroke(new BasicStroke(3));

        double diam = 30;
        double radius = diam / 2;
        double wd = radius;
        double ht = radius;

//        for (double[] y1 : points) {
//            for (double x1 : y1) {
////                double x = 110.13;
////                double y = 255.23;
//                drawPoint(g2, x1, y1, wd, ht,Color.red);
//            }
//        }

//        g2.setColor(Color.magenta);g2.fillOval(15,140,100,50);//涂椭圆


//        g2.setComposite(AlphaComposite.SrcAtop);
//        g2.drawImage(image, 0, 0, null);
//        g2.dispose();
//        FileOutputStream os = new FileOutputStream(imgPath + "a.png");
//        ImageIO.write(output, "PNG", os);
        return output;
    }


    public static void drawPoint(Graphics2D g2, double x, double y, double width, double height, Color color) {
//        Jpanel
//        Canvas
//        SVGPath
//        Line2D.Double
//        Ellipse2D.Double
//        RoundRectangle2D.Float
//        g2.drawOval();
        // 默认画笔白色
//        g2.setColor(Color.cyan);g2.drawOval(x,y,width,height);//画椭圆
//        g2.setColor(Color.cyan);g2.fillOval(x,y,width,height);//涂圆块
//        g2.setColor(Color.red);g2.drawArc(x,y,width,height,10,10);
//        g2.fillArc(x,y,width,height,10,10);

        Ellipse2D.Double aDouble = new Ellipse2D.Double(x, y, width, height);
//        Line2D.Double aDouble = new Line2D.Double(x, y, width, height);
        g2.setColor(color);
        g2.draw(aDouble);

        g2.setColor(color);
        g2.fill(aDouble);

    }


    public static void main(String[] args) throws IOException {
        System.out.println("start");
        getImg("");
        System.out.println("ok");
    }
}
