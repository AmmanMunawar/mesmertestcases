package com.ebricks.script.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

    private static final Logger LOGGER = LogManager.getLogger(Configuration.class.getName());

    private static Configuration instance;
    private static String projectKey;
    @JsonProperty("EXECUTION_DATE")
    private String EXECUTION_DATE;
    @JsonProperty("BUILD")
    private Build BUILD;
    @JsonProperty("EXECUTION_TYPE")
    private int EXECUTION_TYPE;
    @JsonProperty("WALK")
    private Walk WALK;
    @JsonProperty("EXECUTION_MODE")
    private ExecutionMode EXECUTION_MODE;
    @JsonProperty("DEVICE")
    private Device DEVICE;
    @JsonProperty("RESULT_ID")
    private String RESULT_ID;
    @JsonProperty("PROJECT_ID")
    private String PROJECT_ID;
    @JsonProperty("USER_NAME")
    private String USER_NAME;
    @JsonProperty("PASSWORD")
    private String PASSWORD;
    @JsonProperty("UNINSTALL_AFTER_EXECUTION")
    private boolean UNINSTALL_AFTER_EXECUTION;
    @JsonProperty("DASHBOARD_URL")
    private String DASHBOARD_URL;
    @JsonProperty("NODE_URL")
    private String NODE_URL;
    @JsonProperty("TESTCASES")
    private List<Testcase> TESTCASES = new ArrayList<>();
    @JsonProperty("USERID")
    private String USERID;
    @JsonProperty("APP_INFO")
    private  String APP_INFO;
    @JsonProperty("APPIUM_PORT")
    private int APPIUM_PORT;
    @JsonProperty("WDA_LOCAL_PORT")
    private int WDA_LOCAL_PORT;
    @JsonProperty("EXECUTION")
    private Execution EXECUTION;


    private Configuration(){}

    public static Configuration getInstance() {

        if (instance == null) {

            ObjectMapper objectMapper = new ObjectMapper();
            try {

                instance = objectMapper.readValue(new FileReader(
                                System.getProperty("user.dir") + "/executions/" +
                                        Configuration.getProjectKey()+"/execution-config.json")
                        , Configuration.class);

            } catch (IOException e) {

                LOGGER.error("Configguration Exception",e);
            }

            return instance;
        }

        return instance;
    }


    public static String getProjectKey() {
        return projectKey;
    }

    public static void setProjectKey(String projectKey) {
        Configuration.projectKey = projectKey;
    }

    public String getEXECUTION_DATE() {
        return EXECUTION_DATE;
    }

    public void setEXECUTION_DATE(String EXECUTION_DATE) {
        this.EXECUTION_DATE = EXECUTION_DATE;
    }

    public Build getBUILD() {
        return BUILD;
    }

    public void setBUILD(Build BUILD) {
        this.BUILD = BUILD;
    }

    public int getEXECUTION_TYPE() {
        return EXECUTION_TYPE;
    }

    public void setEXECUTION_TYPE(int EXECUTION_TYPE) {
        this.EXECUTION_TYPE = EXECUTION_TYPE;
    }

    public Walk getWALK() {
        return WALK;
    }

    public void setWALK(Walk WALK) {
        this.WALK = WALK;
    }

    public ExecutionMode getEXECUTION_MODE() {
        return EXECUTION_MODE;
    }

    public void setEXECUTION_MODE(ExecutionMode EXECUTION_MODE) {
        this.EXECUTION_MODE = EXECUTION_MODE;
    }

    public Device getDEVICE() {
        return DEVICE;
    }

    public void setDEVICE(Device DEVICE) {
        this.DEVICE = DEVICE;
    }

    public String getRESULT_ID() {
        return RESULT_ID;
    }

    public void setRESULT_ID(String RESULT_ID) {
        this.RESULT_ID = RESULT_ID;
    }

    public String getPROJECT_ID() {
        return PROJECT_ID;
    }

    public void setPROJECT_ID(String PROJECT_ID) {
        this.PROJECT_ID = PROJECT_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public boolean isUNINSTALL_AFTER_EXECUTION() {
        return UNINSTALL_AFTER_EXECUTION;
    }

    public void setUNINSTALL_AFTER_EXECUTION(boolean UNINSTALL_AFTER_EXECUTION) {
        this.UNINSTALL_AFTER_EXECUTION = UNINSTALL_AFTER_EXECUTION;
    }

    public String getDASHBOARD_URL() {
        return DASHBOARD_URL;
    }

    public void setDASHBOARD_URL(String DASHBOARD_URL) {
        this.DASHBOARD_URL = DASHBOARD_URL;
    }

    public String getNODE_URL() {
        return NODE_URL;
    }

    public void setNODE_URL(String NODE_URL) {
        this.NODE_URL = NODE_URL;
    }

    public List<Testcase> getTESTCASES() {
        return TESTCASES;
    }

    public void setTESTCASES(List<Testcase> TESTCASES) {
        this.TESTCASES = TESTCASES;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getAPP_INFO() {
        return APP_INFO;
    }

    public void setAPP_INFO(String APP_INFO) {
        this.APP_INFO = APP_INFO;
    }

    public int getAPPIUM_PORT() {
        return APPIUM_PORT;
    }

    public void setAPPIUM_PORT(int APPIUM_PORT) {
        this.APPIUM_PORT = APPIUM_PORT;
    }

    public int getWDA_LOCAL_PORT() {
        return WDA_LOCAL_PORT;
    }

    public void setWDA_LOCAL_PORT(int WDA_LOCAL_PORT) {
        this.WDA_LOCAL_PORT = WDA_LOCAL_PORT;
    }

    public Execution getEXECUTION() {
        return EXECUTION;
    }

    public void setEXECUTION(Execution EXECUTION) {
        this.EXECUTION = EXECUTION;
    }
}
