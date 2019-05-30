package com.ebricks.script;

import com.ebricks.script.executor.StepExecutorResponceWrapper;
import com.ebricks.script.service.AppiumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

        try {

            FileWriter fileWriter = new FileWriter(Path.getinstance().getDomPath() + "/" + id + ".xml");
            fileWriter.write(AppiumService.getInstance().getPageSourse());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id + ".jpg";
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

    public String readXMLFile(String filePath) {

        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {

                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {

            LOGGER.error("Read XML File Exception", e);
        }
        return contentBuilder.toString();
    }
}
