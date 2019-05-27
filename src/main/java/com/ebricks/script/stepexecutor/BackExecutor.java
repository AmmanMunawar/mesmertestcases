package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepResponse;

public class BackExecutor extends StepExecutor {

    public BackExecutor(Step step) {
        super(step);
    }

    public StepResponse execute(UIElement uiElement) {
        init();
        AppiumService.getInstance().back();
        this.stepResponse.setUiElement(uiElement);
        this.stepResponse.getStepStatus().setStatus(true);
        return this.stepResponse;
    }
}
