package com.liumapp.xps;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.liumapp.util.SpringLocator;


/**
 * Hello world!
 *
 */
@Component
public class App 
{  
	@Autowired
    private  Xps2pdf xps2pdf;
    private static String path;
    private static Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args )
    {
        SpringLocator.applicationContext = new ClassPathXmlApplicationContext("classpath*:/applicationContext*.xml");
    	 Scanner sc = new Scanner(System.in);
    	 System.out.println("请输入文件绝对路径:");
    	  path = sc.nextLine();
          Xps2pdf xps =  SpringLocator.getBean(Xps2pdf.class);
          xps.word2Pdf(path,path.substring(0,path.lastIndexOf("."))+".pdf");
    }
 
}
