package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepResponse;

public abstract class StepExecutor {
    protected Step step;
    protected StepResponse stepResponse;

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
        AppiumService.getInstance().getScreenShotAs(this.step.getId());
        AppiumService.getInstance().savePageResourceinFile(this.step.getId());
        stepResponse = new StepResponse();
        stepResponse.getScreen().setScreenshotName(String.valueOf(this.step.getId()));
        stepResponse.getScreen().setDom(AppiumService.getInstance().getPageSourse());
    }

    public abstract StepResponse execute(UIElement uiElement);
}
