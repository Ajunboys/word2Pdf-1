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

    public void testOrder () {
        Queen queen = new Queen();
        try {
            queen.connect();
            queen.say("give me a word");
            System.out.println(queen.hear());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
