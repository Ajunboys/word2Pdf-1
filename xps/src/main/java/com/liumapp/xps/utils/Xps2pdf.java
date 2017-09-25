package com.liumapp.xps.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.liumapp.common.oss.utils.OssUtil;
import com.liumapp.pattern.config.Orderpattern;
import org.springframework.stereotype.Component;

@Component
public class Xps2pdf {

	private final String OFFICE_PATH = "C:/office/";

	private final String  OSS_PATH = "office/";

	static final int wdFormatPDF = 17;// PDF 格式

	static final int ppSaveAsPDF = 32;// ppt 转PDF 格式
	
	/**
	 * 利用xps将word文档转换成pdf格式
	 * @param sfileName 文件起始路径
	 * @param toFileName 
	 * @return 转换成功的pdf文件
	 */
	public  File word2Pdf (String sfileName,String toFileName) {
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

	/**
	 *利用xps将excel文档转换成pdf格式
	 * @param inFilePath  文件路径
	 * @param outFilePath 转换后的文件保存路径
	 * @return
	 */
	public  boolean xls2Pdf (String inFilePath,String outFilePath) {
		ComThread.InitSTA(true);
		ActiveXComponent ax=new ActiveXComponent("Excel.Application");
		try{
			ax.setProperty("Visible", new Variant(false));
			ax.setProperty("AutomationSecurity", new Variant(3)); //禁用宏
			Dispatch excels=ax.getProperty("Workbooks").toDispatch();

			Dispatch excel=Dispatch.invoke(excels,"Open",Dispatch.Method,new Object[]{
							inFilePath,
							new Variant(false),
							new Variant(false)
					},
					new int[9]).toDispatch();
			//转换格式
			Dispatch.invoke(excel,"ExportAsFixedFormat",Dispatch.Method,new Object[]{
					new Variant(0), //PDF格式=0
					outFilePath,
					new Variant(0)  //0=标准 (生成的PDF图片不会变模糊) 1=最小文件 (生成的PDF图片糊的一塌糊涂)
			},new int[1]);

			Dispatch.call(excel, "Close",new Variant(false));

			if (ax!=null) {
				ax.invoke("Quit",new Variant[]{});
				ax=null;
			}
			ComThread.Release();
			return true;
		}catch(Exception es){
			es.printStackTrace();
		}
		return false;
	}

	/**
	 * 将ppt文件转换成pdf格式
	 * @param inputFile 文件的路径
	 * @param pdfFile  转换成功后的文件保存路径
	 * @return
	 */
	public boolean ppt2PDF(String inputFile, String pdfFile) {
		try {
			ComThread.InitSTA(true);
			ActiveXComponent app = new ActiveXComponent("KWPP.Application");
			//            app.setProperty("Visible", false);
			System.out.println("开始转化PPT为PDF...");
			long date = new Date().getTime();
			Dispatch ppts = app.getProperty("Presentations").toDispatch();
			Dispatch ppt = Dispatch.call(ppts, "Open", inputFile, true, // ReadOnly
					//    false, // Untitled指定文件是否有标题
					false// WithWindow指定文件是否可见
			).toDispatch();
			Dispatch.invoke(ppt, "SaveAs", Dispatch.Method, new Object[]{
					pdfFile,new Variant(32)},new int[1]);
			System.out.println("PPT");
			Dispatch.call(ppt, "Close");
			long date2 = new Date().getTime();
			int time = (int) ((date2 - date) / 1000);
			app.invoke("Quit");
			return true;
		} catch (Exception e) {
			   e.printStackTrace();
			return false;
		}
	}
	public String wordToPdf (Orderpattern orderpattern) {
		Xps2pdf xps = new Xps2pdf();
		List<String> list =xps.downFile(orderpattern);
        File file = xps.word2Pdf(list.get(0), list.get(1));
        if(file != null){
        	deleteFile(list.get(0));
		    return list.get(1);
		}
        return null;
		
	}

	public String pptToPdf (Orderpattern orderpattern) {
		Xps2pdf xps = new Xps2pdf();
		List<String> list =xps.downFile(orderpattern);
		System.out.println("-------------"+list.size()+"-------------");
		boolean bool = xps.ppt2PDF(list.get(0), list.get(1));
		if(bool==true){
			deleteFile(list.get(0));
			return list.get(1);
		}
		return null;
	}

	public String xlsToPdf (Orderpattern orderpattern) {
		Xps2pdf xps = new Xps2pdf();
		List<String> list =xps.downFile(orderpattern);
		System.out.println("-------------"+list.size()+"-------------");
		boolean bool = xps.xls2Pdf(list.get(0), list.get(1));
		if(bool==true){
			deleteFile(list.get(0));
			return list.get(1);
		}
		return null;
	}

	/**
	 * 文件下载
	 * @param orderpattern
	 * @return 文件保存的路径
	 */
	public List<String> downFile (Orderpattern orderpattern) {
		OssUtil ossUtil = new OssUtil();
		List<String> pathList = new ArrayList<String>();
		String sfileName = orderpattern.getFileName()+"."+orderpattern.getType();
		File file = new File(OFFICE_PATH);
		if(!file.exists()) {
			file.mkdir();
		}
		String tempPath = OFFICE_PATH+orderpattern.getFileName()+"."+orderpattern.getType();
		String pdfPath =  OFFICE_PATH+orderpattern.getFileName()+".pdf";
		System.out.println(OSS_PATH+sfileName);
		ossUtil.downloadFile(OSS_PATH+sfileName,new File(tempPath));
		pathList.add(tempPath);
		pathList.add(pdfPath);
		return pathList;
	}

	/**
	 * 删除本地临时保存的文件
	 * @param path
	 */
	public void deleteFile (String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	public String getOFFICE_PATH() {
		return OFFICE_PATH;
	}
}
