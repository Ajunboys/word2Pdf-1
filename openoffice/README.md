Office转PDF工具类
=

包含如下子项目

- Office2PDF  工具类,

- App :调用工具类



### 基本说明

* 首先安装OpenOffice,记住安装的路径,我安装的是在C:/Program Files (x86)/OpenOffice 4

   下载安装官方网址:http://www.openoffice.org/ 

* Office2PDF.java类:

   1. 支持Office2003-2007全部格式的文档转PDF:
										.doc|.doc|.docx|.xls|.xlsx|.ppt|.pptx
												
   2. inputFilePath:文件原路径,outputFilePath:目标文件路径
				
   3. getFilePDF();为主方法,调用此方法可完成转换
				
   4. getOfficeManager();此方法是获取OpenOffice 4的安装目录并开启OpenOffice
				
   5. office2pdf();此方法为转换方法,转换成功返回true,转换失败则返回false
				
### 使用说明:		
 
   1. 当调用此工具类时在此类中传入 inputFilePath 和 outputFilePath
   
   根据传入的文件路径调用转换方法(office2pdf())方法.
   	
   	
   	
### 注意事项

   1. inputFilePath 和 outputFilePath 均为绝对路径	
   
   2. jodconverter-core-3.0-beta-4.jar	为主要包,此包目前不能再Maven中使用,若想使用需要在主工程中建立lib目录,并把此包放入lib目录中,
   
   在pom.xml中指定此包.
   
   3. 切记:pom.xml与lib目录必须同级		