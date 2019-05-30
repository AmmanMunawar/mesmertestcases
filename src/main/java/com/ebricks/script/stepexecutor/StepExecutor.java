package com.ebricks.script.stepexecutor;

import com.ebricks.script.Path;
import com.ebricks.script.ScriptReadWriteFromFile;
import com.ebricks.script.executor.FindElement;
import com.ebricks.script.executor.ScriptExecutor;
import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.model.event.InputEvent;
import com.ebricks.script.model.event.TapEvent;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepExecutorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class StepExecutor {

    private static final Logger LOGGER = LogManager.getLogger(StepExecutor.class.getName());
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

        ScriptExecutor scriptExecutor = new ScriptExecutor();
        try {

            LOGGER.info(this.step.getEvent().getType());
            if (step.getEvent().getType().equals("tap")) {

                TapEvent tapEvent = (TapEvent) this.step.getEvent();
                UIElement recordedUIElement = FindElement.getInstance().
                        findUIElementByXY(
                                tapEvent.getX(), tapEvent.getY(), AppiumService.getInstance().getPageSourse());

                UIElement detectUIElement = scriptExecutor.findUIElement(

                        ScriptReadWriteFromFile.getInstance().readXMLFile(Path.getinstance().getTestCasePath() + "testcases01/dom/" + this.step.getScreen().getDom()),
                        recordedUIElement);
                try {
//                    LOGGER.info(uiElement.getText());
                    this.step.setElement(detectUIElement);
                } catch (Exception e) {

                    LOGGER.error("UIElement Exception", e);
                }
            } else if (step.getEvent().getType().equals("input")) {

                InputEvent inputEvent = (InputEvent) this.step.getEvent();
                UIElement recordedUIElement = FindElement.getInstance().
                        findUIElementByXY(
                                inputEvent.getX(), inputEvent.getY(), AppiumService.getInstance().getPageSourse());
                UIElement detectUIElement = scriptExecutor.findUIElement(
                        ScriptReadWriteFromFile.getInstance().readXMLFile(Path.getinstance().getTestCasePath() + "testcases01/dom/" + this.step.getScreen().getDom()),
                        recordedUIElement);
                try {
//                    LOGGER.info(uiElement.getText());
                    this.step.setElement(detectUIElement);
                } catch (Exception e) {

                    LOGGER.error("UIElement Exception", e);
                }
            }

            String screenShotName = AppiumService.getInstance().getScreenShotAs(this.step.getId());
            String domName = ScriptReadWriteFromFile.getInstance().savePageResource(this.step.getId());
            stepExecutorResponse = new StepExecutorResponse();
            stepExecutorResponse.getScreen().setScreenshotName(screenShotName);
            stepExecutorResponse.getScreen().setDom(domName);
        } catch (Exception e) {

            LOGGER.error("StepExecutorException", e);
        }
    }

    public abstract StepExecutorResponse execute();
}