package com.zh.demo.common;


/**
 * 基础工具类
 * 尽量少定义工具类
 *
 * @author zh
 */
public interface  BaseUtils {

    String DEV  = "dev";
    String TEST = "test";
    String PROD = "prod";


    /**
     * 是生产环境
     *
     * @author zh
     */
    static boolean isProd(String active){
        return PROD.equalsIgnoreCase(active);
    }

    /**
     * 是开发环境
     *
     * @param active
     * @return
     */
    static boolean isDevelopment(String active){
        return PROD.equalsIgnoreCase(active);
    }
    /**
     * 是测试环境
     *
     * @author zh
     */
    static boolean isTest(String active){
        return PROD.equalsIgnoreCase(active);
    }

    default void test(){
    }

    public static void main(String[] args) {
        System.out.println("1");
    }
}
