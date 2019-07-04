package com.ebricks.script.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Execution {

    @JsonProperty("nodePath")
    private String nodePath;
    @JsonProperty("appiumPath")
    private String appiumPath;
    @JsonProperty("adbPath")
    private String adbPath;
    @JsonProperty("buildToolsPath")
    private String buildToolsPath;
    @JsonProperty("xcrunPath")
    private String xcrunPath;
    @JsonProperty("fbsimctlPath")
    private String fbsimctlPath;
    @JsonProperty("visionServiceUrl")
    private String visionServiceUrl;
    @JsonProperty("imageCompareServiceUrl")
    private String imageCompareServiceUrl;
    @JsonProperty("proxyServiceUrl")
    private String proxyServiceUrl;
    @JsonProperty("domUtilityServiceUrl")
    private String domUtilityServiceUrl;
    @JsonProperty("visionServiceEnabled")
    private String visionServiceEnabled;
    @JsonProperty("imageCompareServiceEnabled")
    private String imageCompareServiceEnabled;
    @JsonProperty("domServiceEnabled")
    private String domServiceEnabled;
    @JsonProperty("proxyServiceEnabled")
    private String proxyServiceEnabled;
    @JsonProperty("anacondaEnvName")
    private String anacondaEnvName;
    @JsonProperty("anacondaActivatePath")
    private String anacondaActivatePath;
    @JsonProperty("autoHandleIOSSystemDialog")
    private String autoHandleIOSSystemDialog;
    @JsonProperty("pythonPath")
    private String pythonPath;
    @JsonProperty("textClassifierEnabled")
    private String textClassifierEnabled;
    @JsonProperty("vaVisualizerEnabled")
    private String vaVisualizerEnabled;
    @JsonProperty("customWDAPath")
    private String customWDAPath;

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public String getAppiumPath() {
        return appiumPath;
    }

    public void setAppiumPath(String appiumPath) {
        this.appiumPath = appiumPath;
    }

    public String getAdbPath() {
        return adbPath;
    }

    public void setAdbPath(String adbPath) {
        this.adbPath = adbPath;
    }

    public String getBuildToolsPath() {
        return buildToolsPath;
    }

    public void setBuildToolsPath(String buildToolsPath) {
        this.buildToolsPath = buildToolsPath;
    }

    public String getXcrunPath() {
        return xcrunPath;
    }

    public void setXcrunPath(String xcrunPath) {
        this.xcrunPath = xcrunPath;
    }

    public String getFbsimctlPath() {
        return fbsimctlPath;
    }

    public void setFbsimctlPath(String fbsimctlPath) {
        this.fbsimctlPath = fbsimctlPath;
    }

    public String getVisionServiceUrl() {
        return visionServiceUrl;
    }

    public void setVisionServiceUrl(String visionServiceUrl) {
        this.visionServiceUrl = visionServiceUrl;
    }

    public String getImageCompareServiceUrl() {
        return imageCompareServiceUrl;
    }

    public void setImageCompareServiceUrl(String imageCompareServiceUrl) {
        this.imageCompareServiceUrl = imageCompareServiceUrl;
    }

    public String getProxyServiceUrl() {
        return proxyServiceUrl;
    }

    public void setProxyServiceUrl(String proxyServiceUrl) {
        this.proxyServiceUrl = proxyServiceUrl;
    }

    public String getDomUtilityServiceUrl() {
        return domUtilityServiceUrl;
    }

    public void setDomUtilityServiceUrl(String domUtilityServiceUrl) {
        this.domUtilityServiceUrl = domUtilityServiceUrl;
    }

    public String getVisionServiceEnabled() {
        return visionServiceEnabled;
    }

    public void setVisionServiceEnabled(String visionServiceEnabled) {
        this.visionServiceEnabled = visionServiceEnabled;
    }

    public String getImageCompareServiceEnabled() {
        return imageCompareServiceEnabled;
    }

    public void setImageCompareServiceEnabled(String imageCompareServiceEnabled) {
        this.imageCompareServiceEnabled = imageCompareServiceEnabled;
    }

    public String getDomServiceEnabled() {
        return domServiceEnabled;
    }

    public void setDomServiceEnabled(String domServiceEnabled) {
        this.domServiceEnabled = domServiceEnabled;
    }

    public String getProxyServiceEnabled() {
        return proxyServiceEnabled;
    }

    public void setProxyServiceEnabled(String proxyServiceEnabled) {
        this.proxyServiceEnabled = proxyServiceEnabled;
    }

    public String getAnacondaEnvName() {
        return anacondaEnvName;
    }

    public void setAnacondaEnvName(String anacondaEnvName) {
        this.anacondaEnvName = anacondaEnvName;
    }

    public String getAnacondaActivatePath() {
        return anacondaActivatePath;
    }

    public void setAnacondaActivatePath(String anacondaActivatePath) {
        this.anacondaActivatePath = anacondaActivatePath;
    }

    public String getAutoHandleIOSSystemDialog() {
        return autoHandleIOSSystemDialog;
    }

    public void setAutoHandleIOSSystemDialog(String autoHandleIOSSystemDialog) {
        this.autoHandleIOSSystemDialog = autoHandleIOSSystemDialog;
    }

    public String getPythonPath() {
        return pythonPath;
    }

    public void setPythonPath(String pythonPath) {
        this.pythonPath = pythonPath;
    }

    public String getTextClassifierEnabled() {
        return textClassifierEnabled;
    }

    public void setTextClassifierEnabled(String textClassifierEnabled) {
        this.textClassifierEnabled = textClassifierEnabled;
    }

    public String getVaVisualizerEnabled() {
        return vaVisualizerEnabled;
    }

    public void setVaVisualizerEnabled(String vaVisualizerEnabled) {
        this.vaVisualizerEnabled = vaVisualizerEnabled;
    }

    public String getCustomWDAPath() {
        return customWDAPath;
    }

    public void setCustomWDAPath(String customWDAPath) {
        this.customWDAPath = customWDAPath;
    }
}
