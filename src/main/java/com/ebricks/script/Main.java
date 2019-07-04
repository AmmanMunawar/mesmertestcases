package com.ebricks.script;

import com.ebricks.script.config.Configuration;
import com.ebricks.script.executor.ScriptExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    private static ScriptExecutor scriptExecutor = ScriptExecutor.getInstance();

    public static void main(String[] args) {

        System.out.println("Starting the process");

        try {

            Configuration.setProjectKey(args[0]);
            scriptExecutor.init();
            scriptExecutor.process();
        } catch (Exception e) {

            LOGGER.error("Exception", e);
        } finally {

            scriptExecutor.end();
        }

    }
}

