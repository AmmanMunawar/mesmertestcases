package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;

public class LockExecutor extends StepExecutor {

    public LockExecutor(Step step) {
        super(step);
    }

    public Step execute() {

        AppiumService.getInstance().lockDevice();
        return this.stepExecutorResponse;
    }
}
