package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepResponse;

public class LockExecutor extends StepExecutor {
    public LockExecutor(Step step) {
        super(step);
    }

    public void init() {
        AppiumService.getInstance().getScreenShotAs(this.step.getId());
    }


    public StepResponse execute(UIElement uiElement) {
        init();
        AppiumService.getInstance().lockDevice();
        this.stepResponse.setUiElement(uiElement);
        this.stepResponse.getStepStatus().setStatus(true);
        return this.stepResponse;
    }
}
