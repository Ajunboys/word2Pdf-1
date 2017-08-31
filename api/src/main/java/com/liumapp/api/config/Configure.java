package com.liumapp.api.config;

import org.springframework.stereotype.Component;

/**
 * Created by liumapp on 8/31/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class Configure {

    public static String FILE_PATH = "/usr/local/word2pdf";

    /**
     * 测试模式
     * true:输出相当多的debug信息
     * false:不输出无必要的log信息
     */
    public static boolean DEBUG = true;

}
