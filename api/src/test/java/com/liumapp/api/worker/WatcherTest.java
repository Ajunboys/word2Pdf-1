
package com.liumapp.api.worker;

import com.liumapp.DNSQueen.queen.Queen;
import jdk.nashorn.internal.ir.annotations.Ignore;
import junit.framework.TestCase;

import java.io.IOException;


/**
 * Created by liumapp on 9/1/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http: www.liumapp.com
 */
 public class WatcherTest extends TestCase {

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

             queen.say("C:/office2pdf/data_test0_docx_52012");
             System.out.println(queen.hear());
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     @Override
     protected void setUp () throws Exception {

         queen = new Queen();
         queen.setAddress("118.190.136.193");
         queen.connect();

     }

 }
