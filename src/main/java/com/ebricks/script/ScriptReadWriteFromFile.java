package com.ebricks.script;

import com.ebricks.script.executor.StepExecutorResponceWrapper;
import com.ebricks.script.service.AppiumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

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

    public String savePageResource(int id) {

        String filename = id+".xml";
        try {
            FileUtils.writeStringToFile(new File(Path.getinstance().getDomPath() + "/" + filename),AppiumService.getInstance().getPageSourse());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public void saveStepExecutorReponse(StepExecutorResponceWrapper stepExecutorResponces) {

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new FileWriter(
                            Path.getinstance().getResultPath() + "/stepExecutorResponse.json")
                    , stepExecutorResponces);
        } catch (IOException e) {

            LOGGER.error("FileWriter", e);
        }
    }

    public String readXMLFile(String filePath) throws IOException {
        return FileUtils.readFileToString(new File(filePath));
    }
}