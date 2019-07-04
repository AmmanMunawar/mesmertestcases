package com.ebricks.script.stepexecutor;

import com.ebricks.script.FindAttributesWeightage;
import com.ebricks.script.Path;
import com.ebricks.script.ScriptReadWriteFromFile;
import com.ebricks.script.assertion.AssertionUDA;
import com.ebricks.script.executor.FindElement;
import com.ebricks.script.executor.FindPointPercentage;
import com.ebricks.script.executor.ScriptExecutor;
import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.model.event.TapEvent;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.service.ServerService;
import com.ebricks.script.validator.ElementValidator;
import com.ebricks.script.validator.ElementValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class StepExecutor {

    private static final Logger LOGGER = LogManager.getLogger(StepExecutor.class.getName());
    protected Step step;
    protected Step stepExecutorResponse;

    public StepExecutor(Step step) {
        this.step = step;
    }

    public void init() {

        System.out.println("StepId");
        LOGGER.info(this.step.getStepId());
        System.out.println("dOM");
        LOGGER.info(this.step.getScreen().getDom());

        FindAttributesWeightage findAttributesWeightage = FindAttributesWeightage.getInstance();
        List<AssertionUDA> assertionUDAS = new ArrayList<>();
        try {

            ScriptReadWriteFromFile.getInstance().downloadDOMFile(this.step.getDomUrl(), this.step.getScreen().getDom());
            ScriptReadWriteFromFile.getInstance().downloadImages(this.step.getScreen().getFullImageUrl(), this.step.getScreen().getImage());

            if (step.getEvent().getType().equals("tap")) {

                TapEvent tapEvent = (TapEvent) this.step.getEvent();

                UIElement recordedUIElement = FindElement.getInstance().
                        findUIElementByXY(
                                tapEvent.getStartX(), tapEvent.getStartY(),
                                ScriptReadWriteFromFile.getInstance().readXMLFile(Path.getinstance().getTestCasePath() + this.step.getScreen().getDom()));
                findAttributesWeightage
                        .findWeightage(recordedUIElement,
                                AppiumService.getInstance().getPageSourse());
                FindPointPercentage.getInstance().findPointsPercentage(tapEvent.getStartX(), tapEvent.getStartY(),
                        recordedUIElement.getBounds());

                //Validating Element
                for (AssertionUDA uda : this.step.getUda()) {

                    ElementValidator elementValidator = ElementValidatorFactory.getInstance().getElementValidator(uda);
                    System.out.println(elementValidator.validate(this.step.getScreen().getDom()));
                    assertionUDAS.add(elementValidator.validate(this.step.getScreen().getDom()));

                }

                UIElement detectUIElement = FindElement.getInstance().findUIElement(
                        AppiumService.getInstance().getPageSourse(),
                        recordedUIElement, tapEvent.getStartX(), tapEvent.getStartY(), false);

                this.step.setElement(detectUIElement);

            } else if (step.getEvent().getType().equals("input")) {

                try {

                    this.step.setElement(this.step.getElement());
                } catch (Exception e) {

                    LOGGER.error("Input  Exception", e);
                }
            }
            //this is for loading
            int loadingTime = 0;
            while (!loading().equals("loaded") || loadingTime > (ScriptExecutor.getWaitTime() + 15000)) {

                Thread.sleep(5000);
                loadingTime = loadingTime + 5000;
            }

            //these are for setting step Response Properties
            AppiumService.getInstance().getScreenShotAs(this.step.getScreen().getImage(), false);
            ScriptReadWriteFromFile.getInstance().savePageResource(this.step.getScreen().getDom());
            this.stepExecutorResponse = new Step();
            this.stepExecutorResponse.setUda(assertionUDAS);
            setStepsResponseProperties();

        } catch (Exception e) {

            LOGGER.error("StepExecutorException", e);
        }
    }

    private void setStepsResponseProperties() {

        this.stepExecutorResponse.setElement(this.step.getElement());
        this.stepExecutorResponse.setResult(this.step.getResult());
        this.stepExecutorResponse.setScreen(this.step.getScreen());//);\getScreen().setImage(screenShotName);
        this.stepExecutorResponse.setEvent(this.step.getEvent());
        this.stepExecutorResponse.getScreen().setDom(this.step.getScreen().getDom());
        this.stepExecutorResponse.getScreen().setFullImageUrl(this.step.getScreen().getFullImageUrl());
        this.stepExecutorResponse.getScreen().setImage(this.step.getScreen().getImage());
        this.stepExecutorResponse.setStepId(this.step.getStepId());
        this.stepExecutorResponse.getResult().setSuccess(true);
        this.stepExecutorResponse.setDomUrl(this.step.getDomUrl());

    }

    private String loading() {

        LOGGER.info("Vission Calling Method");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
        String screenshotName = formatter.format(date) + ".png";
        AppiumService.getInstance().getScreenShotAs(screenshotName, true);
        return ServerService.getInstance().visionServicePost(screenshotName);

    }

    public abstract Step execute();

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}