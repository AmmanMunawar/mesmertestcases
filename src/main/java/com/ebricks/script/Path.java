package com.ebricks.script;

import com.ebricks.script.config.Configuration;

import java.io.File;

public class Path {

    private static Path instance;
    private String basePath = System.getProperty("user.dir");
    private String testCasePath = basePath + "/executions/" +
            Configuration.getProjectKey() + "/testcases/" + Configuration.getInstance().getTESTCASES().get(0).getId() + "/dom/";

    private String imagesPath = basePath + "/executions/" +
            Configuration.getProjectKey() + "/testcases/" + Configuration.getInstance().getTESTCASES().get(0).getId() + "/images/";

    private String scriptPath = System.getProperty("user.dir") +
            "/executions/" + Configuration.getProjectKey() + "/testcases/" +
            Configuration.getInstance().getTESTCASES().get(0).getId() + "/data.json";

    private String outpurImageDir = "executions/" + Configuration.getProjectKey() + "/output/screenshots/";
    private String domOutputDir = "executions/" + Configuration.getProjectKey() + "/output/dom/";
    private String loadingDirPath = "executions/" + Configuration.getProjectKey() + "/output/loading/";

    private Path() {}

    public static Path getinstance() {

        if (instance == null) {
            instance = new Path();
        }
        return instance;
    }

    public void makeDirs() {

        File testCaseDir = new File("executions/" + Configuration.getProjectKey() + "/testcases/" + Configuration.getInstance().getTESTCASES().get(0).getId());
        File outputDir = new File("executions/" + Configuration.getProjectKey() + "/output");
        File testcaseImageDir = new File("executions/" + Configuration.getProjectKey() + "/testcases/" + Configuration.getInstance().getTESTCASES().get(0).getId() + "/images");
        File testcaseDOMDir = new File("executions/" + Configuration.getProjectKey() + "/testcases/" + Configuration.getInstance().getTESTCASES().get(0).getId() + "/dom");
        File outputImageDir = new File("executions/" + Configuration.getProjectKey() + "/output/screenshots/");
        File ouputDOMDir = new File("executions/" + Configuration.getProjectKey() + "/output/dom/");
        File loadingScreenShots = new File("executions/" + Configuration.getProjectKey() + "/output/loading/");

        testcaseDOMDir.mkdir();
        outputDir.mkdirs();
        testCaseDir.mkdirs();
        testcaseImageDir.mkdir();
        outputImageDir.mkdirs();
        ouputDOMDir.mkdirs();
        loadingScreenShots.mkdirs();

    }

    public String getTestCasePath() {
        return testCasePath;
    }

    public String getImagesPath() {
        return imagesPath;
    }

    public String getOutpurImageDir() {
        return outpurImageDir;
    }

    public String getDomOutputDir() {
        return domOutputDir;
    }

    public String getLoadingDirPath() {
        return loadingDirPath;
    }
}
