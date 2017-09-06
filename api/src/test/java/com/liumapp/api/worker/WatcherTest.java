
package com.liumapp.api.worker;

import com.liumapp.DNSQueen.queen.Queen;
import junit.framework.TestCase;

import java.io.IOException;


/**
 * Created by liumapp on 9/1/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
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

    public void testOpenOffice() {
    	         //E:\work\office2pdf\data\test.ppt
        queen.say("E:/work/office2pdf/data_test2_PPT_9572912");
        try {
            System.out.println(queen.hear());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testXps() {
        queen.say("http://oss.aliyun.com/test_testfile_doc_5172912");
        try {
            System.out.println(queen.hear());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setUp() throws Exception {

        queen = new Queen();
        queen.connect();

    }

}

