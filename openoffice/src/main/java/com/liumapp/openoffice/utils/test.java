/*package com.liumapp.openoffice.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.ConnectException;

import org.junit.Test;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
public class test {
	public static int office2PDF(String sourceFile, String destFile) {  
        try {  
            File inputFile = new File(sourceFile);  
            if (!inputFile.exists()) {  
                return -1;// 找不到源文件, 则返回-1  
            }  
  
            // 如果目标路径不存在, 则新建该路径  
            File outputFile = new File(destFile);  
            if (!outputFile.getParentFile().exists()) {  
                outputFile.getParentFile().mkdirs();  
            }  
  
            String OpenOffice_HOME = "C:\\Program Files (x86)\\OpenOffice 4";//这里是OpenOffice的安装目录, 在我的项目中,为了便于拓展接口,没有直接写成这个样子,但是这样是绝对没问题的  
            // 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'  
            if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {  
                OpenOffice_HOME += "\\";  
            }  
            // 启动OpenOffice的服务  
            String command = OpenOffice_HOME  
                    + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";  
            Process pro = Runtime.getRuntime().exec(command);  
            // connect to an OpenOffice.org instance running on port 8100  
            OpenOfficeConnection connection = new SocketOpenOfficeConnection(  
                    "127.0.0.1", 8100);  
            connection.connect();  
  
            // convert  
            DocumentConverter converter = new OpenOfficeDocumentConverter(  
                    connection);  
            converter.convert(inputFile, outputFile);  
  
            // close the connection  
            connection.disconnect();  
            // 关闭OpenOffice服务的进程  
            pro.destroy();  
  
            return 0;  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            return -1;  
        } catch (ConnectException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        return 1;  
    }  
	      @Test
	      public void testWord2Pdf() throws Exception {
	           //String srcPath = "D:/test2.PPT";
	           //String desPath = "D:/test2.pdf";
	           office2PDF("D:/test2.PPT", "D:/test2.pdf");
	       }
    }
		
    
*/