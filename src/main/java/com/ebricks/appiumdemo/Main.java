package com.ebricks.appiumdemo;

import com.ebricks.appiumdemo.TestServices.AppiumDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    private static AppiumDemo appiumDemo = new AppiumDemo();

    public static void main(String[] args) {

        try {

            appiumDemo.init();
            appiumDemo.testProcess();
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            appiumDemo.end();
        }

    }
}
