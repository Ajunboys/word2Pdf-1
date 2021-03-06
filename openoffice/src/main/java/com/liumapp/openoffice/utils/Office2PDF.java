package com.liumapp.openoffice.utils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.liumapp.xps.utils.Xps2pdf;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.stereotype.Component;

import com.liumapp.pattern.config.Orderpattern;

@Component
public class Office2PDF {

	/**
	 * office中.doc格式
	 */
	public static final String OFFICE_DOC = "doc";
	/**
	 * office中.docx格式
	 */
	public static final String OFFICE_DOCX = "docx";
	/**
	 * office中.xls格式
	 */
	public static final String OFFICE_XLS = "xls";
	/**
	 * office中.xlsx格式
	 */
	public static final String OFFICE_XLSX = "xlsx";
	/**
	 * office中.ppt格式
	 */
	public static final String OFFICE_PPT = "PPT";
	/**
	 * office中.pptx格式
	 */
	public static final String OFFICE_PPTX = "pptx";
	/**
	 * pdf格式
	 */
	public static final String OFFICE_TO_PDF = "pdf";
	/**
	 * txt格式
	 */
	public static final String OFFICE_TO_TXT="txt";

	
	/*public static void getFilePDF() throws Exception{
		Office2PDF office2pdf = new Office2PDF();
		//office2pdf.openOfficeToPDF("D:/test." + OFFICE_DOCX, "D:/test_" + new Date().getTime() + "." + OFFICE_TO_PDF);
		office2pdf.office2pdf("D:/test." +OFFICE_DOCX, "D:/test_" + new Date().getTime() + "." + OFFICE_TO_PDF);
		office2pdf.openOfficeToPDF("D:/test." + OFFICE_TO_PDF, null);
	}*/

	/**
	 * 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx) 转化为pdf文件<br>
	 * 
	 * @param inputFilePath
	 *            源文件路径，如："e:/test.docx"
	 * @param outputFilePath
	 *            目标文件路径，如："e:/test_docx.pdf"
	 * @return
	 * @throws Exception 
	 */
	public boolean openOfficeToPDF(String inputFilePath, String outputFilePath) throws Exception {
		return office2pdf(this, inputFilePath, outputFilePath);
	}

	/**
	 * 根据操作系统的名称，获取OpenOffice.org 3的安装目录<br>
	 * 如我的OpenOffice.org 3安装在：C:/Program Files (x86)/OpenOffice.org 3<br>
	 * 
	 * @return OpenOffice.org 3的安装目录
	 */
	public String getOfficeHome() {
		String osName = System.getProperty("os.name");
		if (Pattern.matches("Linux.*", osName)) {
			return "/opt/openoffice.org3";
		} else if (Pattern.matches("Windows.*", osName)) {
			return "C:/Program Files (x86)/OpenOffice 4";
		} else if (Pattern.matches("Mac.*", osName)) {
			return "/Application/OpenOffice.org.app/Contents";
		}
		return null;
	}

	/**
	 * 连接OpenOffice.org 并且启动OpenOffice.org
	 * 
	 * @return
	 */
	public OfficeManager getOfficeManager() {
		DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
		// 获取OpenOffice.org 3的安装目录
		String officeHome = getOfficeHome();
		config.setOfficeHome(officeHome);
		// 启动OpenOffice的服务
		OfficeManager officeManager = config.buildOfficeManager();
		officeManager.start();
		return officeManager;
	}

	/**
	 * 转换文件
	 * 
	 * @param inputFile:源文件
	 * @param outputFilePath_end
	 * @param inputFilePath
	 * @param outputFilePath
	 * @param converter
	 * @throws Exception 
	 */
	public void converterFile(File inputFile, String outputFilePath_end, String inputFilePath, String outputFilePath, OfficeDocumentConverter converter) throws Exception {
		File outputFile = new File(outputFilePath_end);
		// 假如目标路径不存在,则新建该路径
		if (!outputFile.getParentFile().exists()) {
			outputFile.getParentFile().mkdirs();
		}
		converter.convert(inputFile, outputFile);
		System.out.println("文件：" + inputFilePath + "\n转换为\n目标文件：" + outputFile + "\n成功！");
	}

	/**
	 * 使Office2003-2007全部格式的文档(.doc|.docx|.xls|.xlsx|.ppt|.pptx) 转化为pdf文件<br>
	 * 
	 * @param office2PDF
	 * @param inputFilePath
	 *            源文件路径，如："e:/test.docx"
	 * @param outputFilePath
	 *            目标文件路径，如："e:/test_docx.pdf"
	 * @return
	 * @throws Exception 
	 */
	public static boolean office2pdf(Office2PDF office2PDF, String inputFilePath, String outputFilePath) throws Exception {
		boolean flag = false;
		OfficeManager officeManager = office2PDF.getOfficeManager();
		// 连接OpenOffice
		OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
		long begin_time = new Date().getTime();
		if (null != inputFilePath) {
			File inputFile = new File(inputFilePath);
			// 判断目标文件路径是否为空
			if (null == outputFilePath) {
				// 转换后的文件路径
				String outputFilePath_end = office2PDF.getOutputFilePath(inputFilePath);
				if (inputFile.exists()) {// 找不到源文件, 则返回
					office2PDF.converterFile(inputFile, outputFilePath_end, inputFilePath, outputFilePath, converter);
					flag = true;
				}
			} else {
				if (inputFile.exists()) {// 找不到源文件, 则返回
					office2PDF.converterFile(inputFile, outputFilePath, inputFilePath, outputFilePath, converter);
					flag = true;
				}
			}
			officeManager.stop();
		} else {
			System.out.println("con't find the resource");
		}
		long end_time = new Date().getTime();
		System.out.println("文件转换耗时：[" + (end_time - begin_time) + "]ms");
		return flag;
	}

	/**
	 * 获取输出文件
	 * 
	 * @param inputFilePath
	 * @return
	 */
	public String getOutputFilePath(String inputFilePath) {
		String outputFilePath = inputFilePath.replaceAll("." + getPostfix(inputFilePath), ".pdf");
		return outputFilePath;
	}

	/**
	 * 获取inputFilePath的后缀名，如："e:/test.pptx"的后缀名为："pptx"<br>
	 * @param inputFilePath
	 * @return
	 */
	public String getPostfix(String inputFilePath) {
		return inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
	}

	public String test (Orderpattern orderpattern) {

		Office2PDF office2pdf = new Office2PDF();
		Xps2pdf xps = new Xps2pdf();
		List<String> list = xps.downFile(orderpattern);
		boolean bool = false;
		try {
		bool = office2pdf.openOfficeToPDF(list.get(0),list.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bool) {
			xps.deleteFile(list.get(0));
			return list.get(1);
		}
		return null;
	}

	public static void main(String[] args) {
		Office2PDF of = new Office2PDF();
		try {
			of.openOfficeToPDF("C:\\ppt\\test.ppt","C:\\ppt\\test.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
