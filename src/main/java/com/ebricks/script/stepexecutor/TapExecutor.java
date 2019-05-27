package com.ebricks.script.stepexecutor;

import com.ebricks.script.executor.ScriptExecutor;
import com.ebricks.script.model.Step;
import com.ebricks.script.model.UIElement;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TapExecutor extends StepExecutor {

    private static final Logger LOGGER = LogManager.getLogger(ScriptExecutor.class.getName());

    public TapExecutor(Step step) {
        super(step);
    }

    public StepResponse execute(UIElement uiElement) {
        init();
        AppiumService.getInstance().click(uiElement);
        this.stepResponse.setUiElement(uiElement);
        this.stepResponse.getStepStatus().setStatus(true);
        return this.stepResponse;
    }
}
