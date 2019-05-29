package com.ebricks.script.stepexecutor;

import com.ebricks.script.FileUtils;
import com.ebricks.script.executor.FindXYElements;
import com.ebricks.script.executor.ScriptExecutor;
import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepExecutorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class StepExecutor {
    private static final Logger LOGGER = LogManager.getLogger(ScriptExecutor.class.getName());
    protected Step step;
    protected StepExecutorResponse stepExecutorResponse;

    public StepExecutor(Step step) {
        this.step = step;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public void init() {

        try {
            if (step.getEvent().getType().equals("tap")) {

                UIElement uiElement = FindXYElements.getInstance().findTapElement(this.step.getEvent());
                this.step.setElement(uiElement);

            } else if (step.getEvent().getType().equals("input")) {
                UIElement uiElement = FindXYElements.getInstance().findInputElement(this.step.getEvent());
                this.step.setElement(uiElement);
            }

            String screenShotName = AppiumService.getInstance().getScreenShotAs(this.step.getId());
            String domName = FileUtils.getInstance().savePageResource(this.step.getId());
            stepExecutorResponse = new StepExecutorResponse();
            stepExecutorResponse.getScreen().setScreenshotName(screenShotName);
            stepExecutorResponse.getScreen().setDom(domName);
        } catch (Exception e) {
            LOGGER.error("StepExecutorException", e);
        }
    }

    public abstract StepExecutorResponse execute();
}