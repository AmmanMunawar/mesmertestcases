package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepResponse;

public class UnlockExecutor extends StepExecutor {
    public UnlockExecutor(Step step) {
        super(step);
    }

    public StepResponse execute(UIElement uiElement) {
        init();
        AppiumService.getInstance().unlockDevice();
        this.stepResponse.setUiElement(uiElement);
        this.stepResponse.getStepStatus().setStatus(true);
        return this.stepResponse;
    }
}
