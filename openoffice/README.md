Office转PDF工具类
=

包含如下子项目

- Office2PDF  工具类,
- App :调用工具类



### 基本说明

* 首先安装OpenOffice,记住安装的路径,我安装的是在C:/Program Files (x86)/OpenOffice 4

* Office2PDF.java类:

    1. 支持Office2003-2007全部格式的文档转PDF:
                                    .doc|.doc|.docx|.xls|.xlsx|.ppt|.pptx
                                    
    2. inputFilePath:文件原路径,outputFilePath:目标文件路径
    
    3. getFilePDF();为主方法,调用此方法可完成转换\
    
    4. getOfficeManager();此方法是获取OpenOffice 4的安装目录并开启OpenOffice
    
    5. office2pdf();此方法为转换方法,转换成功返回true,转换失败则返回false
    
				
### 使用方法
								
* 