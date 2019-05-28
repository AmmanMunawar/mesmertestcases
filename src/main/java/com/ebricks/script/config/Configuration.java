package com.ebricks.script.config;

import com.ebricks.script.executor.ScriptExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;

public class Configuration {
    private static final Logger LOGGER = LogManager.getLogger(ScriptExecutor.class.getName());
    private String deviceName;
    private String platformName;
    private String platformVersion;
    private String automationName;
    private String appiumIP;
    private int appiumPort;

    public String getAppiumIP() {
        return appiumIP;
    }

    public void setAppiumIP(String appiumIP) {
        this.appiumIP = appiumIP;
    }

    public int getAppiumPort() {
        return appiumPort;
    }

    public void setAppiumPort(int appiumPort) {
        this.appiumPort = appiumPort;
    }

    private static Configuration instance;

    public static Configuration getInstance() {
        if (instance == null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                instance = objectMapper.readValue(new FileReader(System.getProperty("user.dir") + "/resources/config.json")
                        , Configuration.class);
            } catch (IOException e) {
                LOGGER.error(e);
            }
            return instance;
        }
        return instance;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getAutomationName() {
        return automationName;
    }

    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

}
