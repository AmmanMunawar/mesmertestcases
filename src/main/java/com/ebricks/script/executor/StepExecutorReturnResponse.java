package com.ebricks.script.executor;

public class StepExecutorReturnResponse {
    private String action;
    private String screenshotname;
    private boolean status;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getScreenshotname() {
        return screenshotname;
    }

    public void setScreenshotname(String screenshotname) {
        this.screenshotname = screenshotname;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
