package com.ebricks.script.config;


import com.ebricks.script.model.Step;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecutionFilesData {

    @JsonProperty("detectElementAtReplay")
    private Boolean detectElementAtReplay;
    @JsonProperty("projectKey")
    private String projectKey;
    @JsonProperty("packageId")
    private String packageId;
    @JsonProperty("group")
    private String group;
    @JsonProperty("description")
    private String description;
    @JsonProperty("testCaseType")
    private String testCaseType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("steps")
    private List<Step> steps = new ArrayList();

    @JsonProperty("detectElementAtReplay")
    public Boolean getDetectElementAtReplay() {
        return detectElementAtReplay;
    }

    @JsonProperty("detectElementAtReplay")
    public void setDetectElementAtReplay(Boolean detectElementAtReplay) {
        this.detectElementAtReplay = detectElementAtReplay;
    }

    @JsonProperty("projectKey")
    public String getProjectKey() {
        return projectKey;
    }

    @JsonProperty("projectKey")
    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    @JsonProperty("packageId")
    public String getPackageId() {
        return packageId;
    }

    @JsonProperty("packageId")
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("testCaseType")
    public String getTestCaseType() {
        return testCaseType;
    }

    @JsonProperty("testCaseType")
    public void setTestCaseType(String testCaseType) {
        this.testCaseType = testCaseType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("steps")
    public List<Step> getSteps() {
        return steps;
    }

    @JsonProperty("steps")
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

}
