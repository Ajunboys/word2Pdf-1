package com.liumapp.api.worker;

import com.liumapp.DNSQueen.worker.ready.StandReadyWorker;
import com.liumapp.api.config.Configure;

//import com.liumapp.openoffice.utils.Office2PDF;
import com.liumapp.api.module.ResultMsg;
import com.liumapp.common.oss.utils.OssUtil;
import com.liumapp.pattern.config.Orderpattern;
import com.liumapp.xps.utils.Xps2pdf;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
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

        if (!orderpattern.getType().equals("html")) {
            map = new HashMap<String,File>();
            map.put("word/"+orderpattern.getFileName()+"."+orderpattern.getType()
                    ,new File(orderpattern.getSavePath()+"/"+orderpattern.getFileName()
                    +"."+orderpattern.getType()));
            //调用xps模块
            Xps2pdf xps2pdf = new Xps2pdf();
            if (orderpattern.getType().toLowerCase().equals("doc") ||
                    orderpattern.getType().equals("docx")) {

                result = xps2pdf.wordToPdf(orderpattern);
            } else if (orderpattern.getType().toLowerCase().equals("ppt") ||
                    orderpattern.getType().toLowerCase().equals("pptx")) {

                result = xps2pdf.pptToPdf(orderpattern);
            } else if (orderpattern.getType().toLowerCase().equals("xls") ||
                    orderpattern.getType().toLowerCase().equals("xlsx")) {

                result = xps2pdf.xlsToPdf(orderpattern);
            }
            if (result.toLowerCase().equals("success")) {
                rsg.setIsSuccess("success");
                String path = orderpattern.getSavePath()+"/"+orderpattern.getFileName() +".pdf";
                File file = new File(path);
                //http://huluwa-pdf.oss-cn-qingdao.aliyuncs.com/pdf/test0.pdf
                rsg.setPdfPath("http://huluwa-pdf.oss-cn-qingdao.aliyuncs.com/pdf/"
                        +orderpattern.getFileName() +".pdf");
                map.put("pdf/"+orderpattern.getFileName() +".pdf",file);
                ossutil.uploadManyFile(map);
            }
        } else if (orderpattern.getType().equals("html")) {
            //调用wkhtmltopdf模块
        } else {
            //调用openoffice模块

//            Office2PDF office2PDF = new Office2PDF();
//            result = office2PDF.test(orderpattern);

        }
        logger.info(result);
        String json = JSONObject.fromObject(rsg).toString();
        return json;
    }

}
