package com.ebricks.script.stepexecutor.response;

import com.ebricks.script.model.UIElement;

public class StepExecutorResponse {

    private Screen screen;
    private StepStatus stepStatus;
    private UIElement uiElement;

    public StepExecutorResponse(){

        this.screen = new Screen();
        this.stepStatus = new StepStatus();
    }

    public Screen getScreen() {
        return screen;
    }

    public StepStatus getStepStatus() {
        return stepStatus;
    }

    public UIElement getUiElement() {
        return uiElement;
    }

    public void setUiElement(UIElement uiElement) {
            this.uiElement = uiElement;
    }


}