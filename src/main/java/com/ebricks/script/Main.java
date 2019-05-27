package com.ebricks.script;

import com.ebricks.script.executor.ScriptExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    private static ScriptExecutor ScriptExecutor = new ScriptExecutor();

    public static void main(String[] args) {

        try {

            ScriptExecutor.init();
            ScriptExecutor.process();
        } catch (Exception e) {
            LOGGER.error("Exception", e);
        } finally {
            ScriptExecutor.end();
        }

    }
}
