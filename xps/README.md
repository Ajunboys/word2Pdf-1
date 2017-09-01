# xps转pdf工具类
* Xp2pdf.java

* App.java

##依赖jar
1. jacob-1.18.jar
##环境要求
* 下载Microsoft Office 在下载一个SaveAsPDFandXPS插件
* 将jacob-1.18.jar解压得到jacob-1.18-x64.dll和jacob-1.18-x86.dll放到到你的jdk/jre/bin下面（不放会报错：java.lang.NoClassDefFoundError: Could not initialize class com.jacob.com.Dispatch）

##支持转换的文档类型
* *.doc *.docx等文档

##调用方法
* word2Pdf(str1,str2)

方法参数:
1. str1 

类型:String 
 
描述:转换文件的地址
 
2. str2

数据类型:String

描述:转换成功后的*.pdf要放的目标路径