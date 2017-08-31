package com.liumapp.api;

import com.liumapp.api.config.Configure;
import org.apache.commons.cli.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by liumapp on 8/31/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class App {

    public static void main(String[] args) {

        try {
            parseArgs(args);
        } catch (ParseException e1) {
            logger.warn("parse args error");
        }

        SpringLocator.applicationContext = new ClassPathXmlApplicationContext(
                "classpath*:/spring/applicationContext*.xml");
        DNSBrood dnsBrood = SpringLocator.getBean(DNSBrood.class);

        try {
            dnsBrood.start();
        } catch (UnknownHostException e) {
            logger.warn("init failed ", e);
        } catch (IOException e) {
            logger.warn("init failed ", e);
        }

        while (!dnsBrood.isShutDown) {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void parseArgs(String[] args) throws ParseException{

        Options options = new Options();
        options.addOption(new Option("d", true, "home path"));
        CommandLineParser commandLineParser = new PosixParser();
        CommandLine commandLine = commandLineParser.parse(options, args);
        readOptions(commandLine);

    }

    private static void readOptions(CommandLine commandLine) {

        if (commandLine.hasOption("d")) {
            String filename = commandLine.getOptionValue("d");
            Configure.FILE_PATH = filename;
        }

    }


}
