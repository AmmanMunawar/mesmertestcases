package com.ebricks.script.stepexecutor;

import com.ebricks.script.model.Step;

public class StepFactory {

    private static StepFactory instance;

    public static StepFactory getInstance() {

        if (instance == null) {

            instance = new StepFactory();
        }
        return instance;
    }

    public StepExecutor getStepExecutor(Step step) {

        if (step.getEvent().getType().equals("tap")) {

            return new TapExecutor(step);
        } else if (step.getEvent().getType().equals("back")) {

            return new BackExecutor(step);
        } else if (step.getEvent().getType().equals("lock")) {

            return new LockExecutor(step);
        } else if (step.getEvent().getType().equals("unlock")) {

            return new UnlockExecutor(step);
        } else if (step.getEvent().getType().equals("home")) {

            return new HomeExecutor(step);
        } else if (step.getEvent().getType().equals("launch")) {

            return new LaunchExecutor(step);
        } else if (step.getEvent().getType().equals("input")) {

            return new InputExecutor(step);
        } else if (step.getEvent().getType().equals("swipe")) {

            return new SwipeExecutor(step);
        } else if (step.getEvent().getType().equals("end")) {

            return new EndExecutor(step);
        }

        return null;
    }
}
