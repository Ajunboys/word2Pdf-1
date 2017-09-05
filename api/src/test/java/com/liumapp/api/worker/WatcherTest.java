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
<<<<<<< HEAD
 public class WatcherTest extends TestCase {
 
     private Queen queen;
 
 
     @Ignore
     public void testOrder () {
         try {
             queen.say("give me a word");
             System.out.println(queen.hear());
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     @Ignore
     public void testOpenOffice() {
         queen.say("http: oss.aliyun.com/test_testfile_ppt_5172912");
         try {
             System.out.println(queen.hear());
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     @Ignore
     public void testXps() {
         queen.say("http: oss.aliyun.com/test_testfile_doc_5172912");
         try {
             System.out.println(queen.hear());
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 
     @Ignore
     public void testWorkInvo() {//D:/Demo/office2pdf/data_test0_docx_218876
             queen.say("C:/office2pdf/data/test0.docx");
             try {
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
=======
//public class WatcherTest extends TestCase {
//
//    private Queen queen;
//
//    @Ignore
//    public void testOrder () {
//        try {
//            queen.say("give me a word");
//            System.out.println(queen.hear());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Ignore
//    public void testOpenOffice() {
//        queen.say("http://oss.aliyun.com/test_testfile_ppt_5172912");
//        try {
//            System.out.println(queen.hear());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Ignore
//    public void testXps() {
//        queen.say("http://oss.aliyun.com/test_testfile_doc_5172912");
//        try {
//            System.out.println(queen.hear());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Ignore
//    public void xpsWorkInvo() {
//    	queen.say("D:/Demo/office2pdf/data_test0_docx_218876");
//        try {
//            System.out.println(queen.hear());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Ignore
//    @Override
//    protected void setUp() throws Exception {
//
//        queen = new Queen();
//        queen.setAddress("118.190.136.193");
//        queen.connect();
//
//    }
//
//}
>>>>>>> f6f9134073059f217431605229a0763fc4790a56
