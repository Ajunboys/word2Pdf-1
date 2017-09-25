
package com.liumapp.api.worker;

import com.liumapp.DNSQueen.queen.Queen;
import com.liumapp.api.module.ResultMsg;
import junit.framework.TestCase;
import org.json.JSONObject;


import java.io.IOException;


/**
 * Created by liumapp on 9/1/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http: www.liumapp.com
 */
 /*public class WatcherTest extends TestCase {

     private Queen queen;


     public void testOrder () {
         try {
             queen.say("give me a word");
             System.out.println(queen.hear());
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     public void testXps() {//C:\office2pdf\data
         try {

             queen.say("C:/word_test0_docx_52012");
            // System.out.println(queen.hear());
             String json = queen.hear();
             JSONObject jsonObj = new JSONObject(json);
             ResultMsg rsg = new ResultMsg();
             rsg.setIsSuccess(jsonObj.getString("isSuccess"));
             rsg.setPdfPath(jsonObj.getString("pdfPath"));
             System.out.println(rsg.getPdfPath());
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     @Override
     protected void setUp () throws Exception {

         queen = new Queen();
        //queen.setAddress("118.190.136.193");
         queen.connect();

     }

 }*/
