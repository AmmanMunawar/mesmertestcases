package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;

public class EndExecutor extends StepExecutor {

    public EndExecutor(Step step) {
        super(step);
    }

    public Step execute() {

        AppiumService.getInstance().quit();
        return this.stepExecutorResponse;
    }
}
