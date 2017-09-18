package com.liumapp.api.worker;

import com.liumapp.DNSQueen.worker.ready.StandReadyWorker;
import com.liumapp.api.config.Configure;

import com.liumapp.api.module.ResultMsg;
import com.liumapp.common.oss.utils.OssUtil;
import com.liumapp.openoffice.utils.Office2PDF;
import com.liumapp.pattern.config.Orderpattern;
import com.liumapp.xps.utils.Xps2pdf;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;


/**
 * Created by liumapp on 9/1/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class Watcher extends StandReadyWorker {

    @Autowired
    private Configure configure;
    @Autowired
    private OssUtil ossutil;


    private Map<String,File> map = null;
    private static Logger logger = Logger.getLogger(Watcher.class);

    public String doWhatYouShouldDo(String s) {
        ResultMsg rsg = new ResultMsg();
        Orderpattern orderpattern = Orderpattern.parse(s);
        String result = "";

        if (!orderpattern.getType().equals("html")&&!orderpattern.getType().equals("ppt")
                &&!orderpattern.getType().equals("pptx")) {
            //调用xps模块
            Xps2pdf xps2pdf = new Xps2pdf();
            if (orderpattern.getType().toLowerCase().equals("doc") ||
                    orderpattern.getType().equals("docx")) {

                result = xps2pdf.wordToPdf(orderpattern);
            } else if (orderpattern.getType().toLowerCase().equals("ppt") ||
                    orderpattern.getType().toLowerCase().equals("pptx")) {

                //result = xps2pdf.pptToPdf(orderpattern);
            } else if (orderpattern.getType().toLowerCase().equals("xls") ||
                    orderpattern.getType().toLowerCase().equals("xlsx")) {

                 result = xps2pdf.xlsToPdf(orderpattern);
                 if (result == null ||result.equals("")) {
                     Office2PDF office2PDF = new Office2PDF();
                     result = office2PDF.test(orderpattern);
                 }
            } else if (orderpattern.getType().toLowerCase().equals("pdf")) {

                result =xps2pdf.getOFFICE_PATH()+orderpattern.getFileName()+".pdf";
            }

        } else if (orderpattern.getType().equals("html")) {
            //调用wkhtmltopdf模块
        } else if (orderpattern.getType().toLowerCase().equals("ppt") ||
                orderpattern.getType().toLowerCase().equals("pptx")) {
            //调用openoffice模块

            Office2PDF office2PDF = new Office2PDF();
            result = office2PDF.test(orderpattern);

        }
        if (result !=null) {
            rsg.setIsSuccess("success");
            String path = result;
            File file = new File(path);
            //pdf文件存放的地址
            String pdfPath = "http://huluwa-pdf.oss-cn-qingdao.aliyuncs.com/pdf/"
                    +orderpattern.getFileName() +".pdf";
            rsg.setPdfPath(pdfPath);
            //文件上传到oss,如果是pdf文件就不用上传啦
            if (!orderpattern.getType().toLowerCase().equals("pdf")) {
                ossutil.uploadFile("pdf/" + orderpattern.getFileName() + ".pdf", file);
                file = new File(result);
                //pdf上传到oss以后删除本地文件
                if (file.exists()) {
                    file.delete();
                }
            }
//                //文件下载到本地
//                System.out.println("pdf/"+orderpattern.getFileName() +".pdf");
//                String temp ="C:/test/";
//                File dir = new File(temp);
//                if (!dir.exists()) {//如果文件夹不存在就创建
//                    dir.mkdir();
//                }
//                path = orderpattern.getFileName()+System.currentTimeMillis() +".pdf";
//                ossutil.downloadFile("pdf/"+orderpattern.getFileName() +".pdf"
//                        ,new File(temp+path)
//                );
//
//                 ItextPdfUtil pdfUtil = new ItextPdfUtil();
//                 String imgDir = "C:/img";
//                 File img = new File(imgDir);
//                 if (!img.exists()) {
//                       img.mkdir();
//                 }
//                try {
//                    List<String> listPath = pdfUtil.pdf2Img(temp+path,imgDir);
//                    if (listPath.size() > 0) {
//                         file = new File(temp+path);
//                         if (file.exists()) {
//                             file.delete();//删除文件
//                         }
//                    }
//                    rsg.setPdf1Path(listPath.get(0));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
        }
        logger.info(result);
        String json = JSONObject.fromObject(rsg).toString();
        return json;
    }

}
