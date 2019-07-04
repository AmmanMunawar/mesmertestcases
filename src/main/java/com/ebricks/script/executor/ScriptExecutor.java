package com.ebricks.script.executor;

import com.ebricks.script.ScriptReadWriteFromFile;
import com.ebricks.script.Path;
import com.ebricks.script.model.ScriptInputData;
import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.service.ServerService;
import com.ebricks.script.stepexecutor.StepExecutor;
import com.ebricks.script.stepexecutor.StepFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ScriptExecutor {

    private static final Logger LOGGER = LogManager.getLogger(ScriptExecutor.class.getName());
    private ScriptInputData scriptInputData;
    private StepExecutorResponceWrapper stepExecutorResponces;
    private static long waitTime;
    private static ScriptExecutor instance;

    private ScriptExecutor() {
    }

    public static ScriptExecutor getInstance() {

        if (instance == null) {
            instance = new ScriptExecutor();
        }
        return instance;
    }

    public void init() {

        AppiumService.getInstance().createSession();
        Path.getinstance().makeDirs();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            if (ServerService.getInstance().isStarted()) {

                String postResponse = ServerService.getInstance().getTestCaseData();
                LOGGER.info("Starting Mapping for Data");
                scriptInputData = objectMapper.readValue(postResponse
                        , ScriptInputData.class);
            }

        } catch (IOException e) {

            LOGGER.error("IOException", e);
        }
    }

    public void process() {

        try {

            stepExecutorResponces = new StepExecutorResponceWrapper();
            boolean isStartTime = false;
            long initialTime = 0;
            long finalTime;

            for (Step step : this.scriptInputData.getData().getExecutionFilesData().getSteps()) {

                System.out.println("********************************************************************");
                LOGGER.info("Steps Executing");

                if (!isStartTime) {

                    initialTime = step.getEvent().getEventTime();
                    Thread.sleep(15000);
                    isStartTime = true;
                }

                finalTime = step.getEvent().getEventTime();
                waitTime = finalTime - initialTime;
                Thread.sleep(waitTime);
                initialTime = finalTime;

                StepExecutor stepExecutor = StepFactory.getInstance().getStepExecutor(step);
                stepExecutor.init();
                Step stepExecutorResponse = stepExecutor.execute();
                this.stepExecutorResponces.getSteps().add(stepExecutorResponse);

                ServerService.getInstance().uploadData(step.getScreen().getImage(), step.getScreen().getDom());

                System.out.println("********************************************************************");
            }

        } catch (Exception e) {
            LOGGER.error("Script Executor Exception", e);
        }
    }

    public void end() {

        ServerService.getInstance().TestCaseComplete();
        ServerService.getInstance().ExecutionComplete();
        ScriptReadWriteFromFile.getInstance().saveStepExecutorReponse(this.stepExecutorResponces);
    }

    public static long getWaitTime() {
        return waitTime;
    }

    public StepExecutorResponceWrapper getStepExecutorResponces() {
        return stepExecutorResponces;
    }

    public ScriptInputData getScriptInputData() {
        return scriptInputData;
    }
}