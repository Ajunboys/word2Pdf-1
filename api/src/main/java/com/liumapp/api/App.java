package com.liumapp.api;

import com.liumapp.api.config.Configure;
import com.liumapp.api.utils.SpringLocator;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by liumapp on 8/31/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class App {

    private boolean isShutDown = false;

    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {

        try {
            parseArgs(args);
        } catch (ParseException e1) {
            logger.warn("parse args error");
        }

        SpringLocator.applicationContext = new ClassPathXmlApplicationContext("classpath*:/spring/applicationContext*.xml");

        App app = SpringLocator.getBean(App.class);

        try {
            app.start();
        }  catch (IOException e) {
            logger.warn("init failed ", e);
        }

        while (!app.isShutDown) {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void start() throws  IOException {
        //do the jobs that Queen asked to do.
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
