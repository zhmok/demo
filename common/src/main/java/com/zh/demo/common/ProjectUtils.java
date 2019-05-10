package com.zh.demo.common;

import static com.zh.demo.common.ProjectConstants.*;

public abstract class ProjectUtils {


    public static boolean isProd(String active){
        return PROD.equalsIgnoreCase(active);
    }
    public static boolean isDev(String active){
        return PROD.equalsIgnoreCase(active);
    }
    public static boolean isTest(String active){
        return PROD.equalsIgnoreCase(active);
    }


}
