package com.ebricks.script;

import com.ebricks.script.executor.ScriptExecutor;
import com.ebricks.script.executor.StepExecutorResponces;
import com.ebricks.script.service.AppiumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    private static final Logger LOGGER = LogManager.getLogger(ScriptExecutor.class.getName());
    private static FileUtils instance;

    private FileUtils() {
    }

    public static FileUtils getInstance() {
        if (instance == null) {
            instance = new FileUtils();
        }
        return instance;
    }

    public String savePageResource(int id) {
        try {
            FileWriter fileWriter = new FileWriter(Path.getinstance().getDirectoryPath() + "/" + id + ".xml");
            fileWriter.write(AppiumService.getInstance().getPageSourse());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id + ".jpg";
    }

    public void saveStepExecutorReponse(StepExecutorResponces stepExecutorResponces) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new FileWriter(Path.getinstance().getDirectoryPath() + "/stepExecutorResponse.json")
                    , stepExecutorResponces);
        } catch (IOException e) {
            LOGGER.error("FileWriter", e);
        }
    }
}
