package com.liumapp.api.worker;

import com.liumapp.DNSQueen.worker.ready.StandReadyWorker;
import com.liumapp.api.config.Configure;
import com.liumapp.openoffice.utils.Office2PDF;
import com.liumapp.pattern.config.Orderpattern;
import com.liumapp.xps.utils.Xps2pdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liumapp on 9/1/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class Watcher extends StandReadyWorker {

    @Autowired
    private Configure configure;

    public String doWhatYouShouldDo(String s) {

        Orderpattern orderpattern = Orderpattern.parse(s);
        String result = "";

        if (orderpattern.getType().equals("doc") || orderpattern.getType().equals("docx")) {
            //调用xps模块
            Xps2pdf xps2pdf = new Xps2pdf();
            result = xps2pdf.test(orderpattern);
        } else if (orderpattern.getType().equals("html")) {
            //调用wkhtmltopdf模块
        } else {
            //调用openoffice模块
            Office2PDF office2PDF = new Office2PDF();
            result = office2PDF.test();
        }

        return result;

    }

}
