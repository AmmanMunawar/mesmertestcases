package com.ebricks.script;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Path {
    private String basePath = System.getProperty("user.dir");
    private String directoryPath = "/resources/results/";
    private static Path instance;

    private Path() {

    }

    public static Path getinstance() {
        if (instance == null) {
            instance = new Path();
        }
        return instance;
    }

    public void makeDirectory() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
        this.directoryPath = this.getBasePath() + this.getDirectoryPath() + "Result-" + formatter.format(date);
        try {
            new File(this.directoryPath).mkdir();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}
