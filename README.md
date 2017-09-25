# word2Pdf

Based on OpenOffice and Xps , turn office word to pdf online , under develop , plz don't use right now

### 项目结构

##### data

数据库ER图

##### lib

导入Maven无法下载到的Jar包

##### api

对外开放模块，也是主模块

##### openoffice

调用openoffice的模块

##### xps

调用windows插件xps的模块


#注意:
该模块放到服务器时,需要在API模块下的target目录下的可执行jar包的MANIFEST.MF
文件中加一个./lib/jodconverter-core-3.0-beta-4.jar
(注意.前面有个空格,还有格式要正)   


