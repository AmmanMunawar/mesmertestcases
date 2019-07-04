package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TapExecutor extends StepExecutor {

    private static final Logger LOGGER = LogManager.getLogger(TapExecutor.class.getName());

    public TapExecutor(Step step) {
        super(step);
    }

    public Step execute() {

        AppiumService.getInstance().click(this.step.getElement());
        return this.stepExecutorResponse;
    }
}
