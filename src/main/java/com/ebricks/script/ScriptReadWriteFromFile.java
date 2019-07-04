package com.ebricks.script;

import com.ebricks.script.config.Configuration;
import com.ebricks.script.executor.StepExecutorResponceWrapper;
import com.ebricks.script.service.AppiumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.*;
import java.net.URL;

public class ScriptReadWriteFromFile {

    private static final Logger LOGGER = LogManager.getLogger(ScriptReadWriteFromFile.class.getName());
    private static ScriptReadWriteFromFile instance;

    private ScriptReadWriteFromFile() {
    }

    public static ScriptReadWriteFromFile getInstance() {

        if (instance == null) {

            instance = new ScriptReadWriteFromFile();
        }
        return instance;
    }

    public String savePageResource(String domName) {

        try {
            FileUtils.writeStringToFile(new File(Path.getinstance().getDomOutputDir() + "/" + domName), AppiumService.getInstance().getPageSourse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return domName;
    }

    public void saveStepExecutorReponse(StepExecutorResponceWrapper stepExecutorResponces) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new FileWriter(
                            "executions/" + Configuration.getProjectKey() + "/output/data.json")
                    , stepExecutorResponces);
        } catch (IOException e) {

            LOGGER.error("FileWriter", e);
        }
    }

    public String readXMLFile(String filePath) throws IOException {
        return FileUtils.readFileToString(new File(filePath));
    }

    public void downloadImages(String URLString, String imageName) throws IOException {

        URL url = new URL(URLString);
        InputStream in = new BufferedInputStream(url.openStream());
        OutputStream out = new BufferedOutputStream(new FileOutputStream(Path.getinstance().getImagesPath() + imageName));
        for (int i; (i = in.read()) != -1; ) {
            out.write(i);
        }
        in.close();
        out.close();
    }

    public void downloadDOMFile(String URLString, String domName) {

        String domString = "";
        String temp;
        try {

            URL url = new URL(URLString);
            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            while ((temp = in.readLine()) != null) {

                domString = domString + temp;
            }
            in.close();
            FileUtils.write(new File(Path.getinstance().getTestCasePath() + "/" + domName), domString);

        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        LOGGER.info("Downloaded !!");

    }

    public void save_reponse(String reseponse) {

        try {

            FileUtils.write(new File("executions/" + Configuration.getProjectKey() + "/testcases/" + Configuration.getInstance().getTESTCASES().get(0).getId() + "/data.json"), reseponse);
        } catch (IOException e) {
            LOGGER.error("Save Response ", e);
        }
    }

}