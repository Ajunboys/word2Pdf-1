package com.liumapp.xps.utils;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.liumapp.pattern.config.Orderpattern;
import org.springframework.stereotype.Component;

@Component
public class Xps2pdf {

	static final int wdFormatPDF = 17;// PDF 格式

	static final int ppSaveAsPDF = 32;// ppt 转PDF 格式
	
	/**
	 * 利用xps将word文档转换成pdf格式
	 * @param sfileName 文件起始路径
	 * @param toFileName 
	 * @return 转换成功的pdf文件
	 */
	public  File word2Pdf(String sfileName,String toFileName){
		 long start = System.currentTimeMillis();      
	        ActiveXComponent app = null;  
	        Dispatch doc = null;  
	        try {      
	            app = new ActiveXComponent("Word.Application");      
	            app.setProperty("Visible", new Variant(false));  
	            Dispatch docs = app.getProperty("Documents").toDispatch();    
	            
	            doc = Dispatch.call(docs,  "Open" , sfileName).toDispatch();  
	            System.out.println("打开文档..." + sfileName);  
	            System.out.println("转换文档到PDF..." + toFileName);
	            File tofile = new File(toFileName);      
	            if (tofile.exists()) {      
	                tofile.delete();      
	            }      
	            Dispatch.call(doc,      
	                          "SaveAs",      
	                          toFileName, // FileName      
	                          wdFormatPDF);      
	            long end = System.currentTimeMillis();  
	            System.out.println("转换完成..用时：" + (end - start) + "ms.");
	            return tofile;

	        } catch (Exception e) {      
	            System.out.println("========Error:文档转换失败：" + e.getMessage()); 
	            return null;
	        } finally {  
	            Dispatch.call(doc,"Close",false);  
	            System.out.println("关闭文档");  
	            if (app != null)      
	                app.invoke("Quit", new Variant[] {});      
	            ComThread.Release();
	            }
	}

	public String xpsWork (Orderpattern orderpattern) {		
		String sfileName = orderpattern.getSavePath()+"/"+orderpattern.getFileName()+"."+orderpattern.getType();
		String toFileName = orderpattern.getSavePath()+"/"+orderpattern.getFileName()+".pdf";
		Xps2pdf xps = new Xps2pdf();
        File file = xps.word2Pdf(sfileName, toFileName);
        if(file != null){
		return "success";
		}
        return null;
		
	}
	
	public String test() {
		return "this is xps test info";
	}

}
