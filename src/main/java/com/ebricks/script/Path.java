package com.ebricks.script;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Path {

    private static Path instance;
    private String basePath = System.getProperty("user.dir");
    private String resultPath = "/resources/results/";
    private String screenShotPath = "/screenshots";
    private String domPath = "/dom";
    private String testCasePath = basePath + "/resources/testcases/";

    private Path() {}

    public static Path getinstance() {

        if (instance == null) {
            instance = new Path();
        }
        return instance;
    }

    public void makeDirectory() {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
        this.resultPath = this.getBasePath() + this.getResultPath() + "Result-" + formatter.format(date);
        this.screenShotPath = this.resultPath + this.screenShotPath;
        this.domPath = this.resultPath + this.domPath;
        try {

            new File(this.resultPath).mkdir();
            new File(this.screenShotPath).mkdir();
            new File(this.domPath).mkdir();
        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public String getTestCasePath() {
        return testCasePath;
    }

    public void setTestCasePath(String testCasePath) {
        this.testCasePath = testCasePath;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public String getScreenShotPath() {
        return screenShotPath;
    }

    public void setScreenShotPath(String screenShotPath) {
        this.screenShotPath = screenShotPath;
    }

    public String getDomPath() {
        return domPath;
    }

    public void setDomPath(String domPath) {
        this.domPath = domPath;
    }
}
