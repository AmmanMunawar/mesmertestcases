package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.model.event.InputEvent;
import com.ebricks.script.service.AppiumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputExecutor extends StepExecutor {

    private static final Logger LOGGER = LogManager.getLogger(InputExecutor.class.getName());

    public InputExecutor(Step step) {
        super(step);
    }

    public Step execute() {

        InputEvent input = (InputEvent) this.step.getEvent();
        AppiumService.getInstance().setValue(this.step.getElement(), input.getText());
        return this.stepExecutorResponse;
    }
}
