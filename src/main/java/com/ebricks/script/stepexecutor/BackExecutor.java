package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;

public class BackExecutor extends StepExecutor {

    public BackExecutor(Step step) {
        super(step);
    }

    public Step execute() {

        AppiumService.getInstance().back();
        return this.stepExecutorResponse;
    }
}
