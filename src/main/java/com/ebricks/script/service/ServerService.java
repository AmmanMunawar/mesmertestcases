package com.ebricks.script.service;

import com.ebricks.script.Path;
import com.ebricks.script.ScriptReadWriteFromFile;
import com.ebricks.script.config.Configuration;
import com.ebricks.script.executor.ScriptExecutor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class ServerService {

    private static final Logger LOGGER = LogManager.getLogger(ServerService.class.getName());
    public static ServerService instance;

    public static ServerService getInstance() {
        if (instance == null) {
            instance = new ServerService();
            return instance;
        }
        return instance;
    }

    public boolean isStarted() {

        String postURL = Configuration.getInstance().getDASHBOARD_URL() + "/api/execution/" + Configuration.getInstance().getRESULT_ID() + "/start";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPostrequest = new HttpPost(postURL);
        try {

            HttpResponse response = httpClient.execute(httpPostrequest);
            if (response.getStatusLine().getStatusCode() == 200) {

                return true;

            }
        } catch (IOException e) {
            LOGGER.error("Starting Server Exception", e);
        }
        return false;
    }


    public String getTestCaseData() throws IOException {

        String postURL = Configuration.getInstance().getDASHBOARD_URL() + "/api/execution/" + Configuration.getInstance().getRESULT_ID() + "/test-case/" + Configuration.getInstance().getTESTCASES().get(0).getId() + "/start";
        System.out.println(postURL);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPostrequest = new HttpPost(postURL);
        HttpResponse response = httpClient.execute(httpPostrequest);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        ScriptReadWriteFromFile.getInstance().save_reponse(responseString);
        return responseString;

    }

    public JSONObject testResultJson() {

        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject testResultJson = new JSONObject();
        testResultJson.put("testCaseResultId", ScriptExecutor.getInstance().getScriptInputData().getData().getTestCaseResultId());
        testResultJson.put("testCaseId", Configuration.getInstance().getTESTCASES().get(0).getId());
        testResultJson.put("testCaseName", Configuration.getInstance().getTESTCASES().get(0).getName());
        try {

            testResultJson.put("additionalInfo", objectMapper.writeValueAsString(ScriptExecutor.getInstance().getStepExecutorResponces()));
        } catch (JsonProcessingException e) {
            LOGGER.error("Response Mapper ", e);
        }
        return testResultJson;
    }

    public void uploadData(String screenshot, String dom) {

        File domFile = new File(Path.getinstance().getDomOutputDir() + dom);
        File screenshotFile = new File(Path.getinstance().getOutpurImageDir() + screenshot);
        String postURL = Configuration.getInstance().getDASHBOARD_URL() + "/api/execution/" + Configuration.getInstance().getRESULT_ID() + "/test-case/" + Configuration.getInstance().getTESTCASES().get(0).getId() + "/data";
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPostrequest = new HttpPost(postURL);
        builder.addTextBody("data", testResultJson().toString());
        if (domFile != null) {

            builder.addBinaryBody("dom", domFile);
            LOGGER.info("File Founded");
        }

        if (screenshotFile != null) {

            builder.addBinaryBody("attachment", screenshotFile);
            LOGGER.info("File Founded");
        }
        httpPostrequest.setEntity(builder.build());
        try {

            HttpResponse response = httpClient.execute(httpPostrequest);
            LOGGER.info(response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                LOGGER.info("Successfully Posted");
            } else {
                LOGGER.error("Error!! While Posting ");
            }
        } catch (IOException e) {
            LOGGER.error("Upload Data Exception", e);
        }
    }

    public void TestCaseComplete() {

        String postURL = Configuration.getInstance().getDASHBOARD_URL() + "/api/execution/" + Configuration.getInstance().getRESULT_ID() + "/test-case/" + Configuration.getInstance().getTESTCASES().get(0).getId() + "/complete";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPostrequest = new HttpPost(postURL);
        try {
            HttpResponse response = httpClient.execute(httpPostrequest);
            System.out.println(response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {

                LOGGER.info("Successfully Hit");

            } else {
                LOGGER.error("Error!! While Hitting URL ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExecutionComplete() {

        String postURL = Configuration.getInstance().getDASHBOARD_URL() + "/api/execution/" + Configuration.getInstance().getRESULT_ID() + "/complete";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPostrequest = new HttpPost(postURL);
        try {
            HttpResponse response = httpClient.execute(httpPostrequest);
            System.out.println(response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {

                LOGGER.info("Successfully Hit");

            } else {
                LOGGER.error("Error!! While Hitting URL ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String visionServicePost(String screenshot) {

        String url = Configuration.getInstance().getEXECUTION().getVisionServiceUrl() + "/api/v1/getLoadApi";
        File loadingScreenShot = new File(Path.getinstance().getLoadingDirPath() + screenshot);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPostrequest = new HttpPost(url);
        String isLoadedResp = null;
        if (loadingScreenShot != null) {

            builder.addBinaryBody("file", loadingScreenShot);
            LOGGER.info("File Founded");
        }
        try {
            httpPostrequest.setEntity(builder.build());
            HttpResponse response = httpClient.execute(httpPostrequest);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            JSONObject jsonObject = new JSONObject(responseString);
            jsonObject = jsonObject.getJSONObject("loadApi");//.getJSONArray("response");
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
            isLoadedResp = jsonObject1.getString("name");

        } catch (IOException e) {
            LOGGER.error("Vission Exception", e);
        }

        return isLoadedResp;

    }
}
