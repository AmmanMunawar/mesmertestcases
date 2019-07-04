package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;
import com.ebricks.script.service.AppiumService;

public class HomeExecutor extends StepExecutor {

    public HomeExecutor(Step step) {
        super(step);
    }

    public Step execute() {

        AppiumService.getInstance().home();
        return this.stepExecutorResponse;
    }
}
