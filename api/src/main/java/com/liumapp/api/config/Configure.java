package com.liumapp.api.config;

import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by liumapp on 8/31/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class Configure {

    public static String FILE_PATH = "/usr/local/word2pdf";

    private SocketAddress fakeDnsServer;

    private int threadNum = 4;

    public final static int PORT = 40214;

    public SocketAddress getFakeDnsServer() {
        return fakeDnsServer;
    }

    public void setFakeDnsServer(String fakeDnsServer) {
        this.fakeDnsServer = new InetSocketAddress(fakeDnsServer, PORT);
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }




}
