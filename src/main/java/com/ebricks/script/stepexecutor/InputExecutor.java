package com.ebricks.script.stepexecutor;

import com.ebricks.script.executor.ScriptExecutor;
import com.ebricks.script.model.Step;
import com.ebricks.script.model.event.InputEvent;
import com.ebricks.script.service.AppiumService;
import com.ebricks.script.stepexecutor.response.StepExecutorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputExecutor extends StepExecutor {
    private static final Logger LOGGER = LogManager.getLogger(ScriptExecutor.class.getName());

    public InputExecutor(Step step) {
        super(step);
    }

    public StepExecutorResponse execute() {
        InputEvent input = (InputEvent) this.step.getEvent();
        AppiumService.getInstance().setValue(this.step.getElement(), input.getText());
        this.stepExecutorResponse.setUiElement(this.step.getElement());
        this.stepExecutorResponse.getStepStatus().setStatus(true);
        return this.stepExecutorResponse;
    }
}
