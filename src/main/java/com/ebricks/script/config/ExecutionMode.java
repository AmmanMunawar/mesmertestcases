package com.ebricks.script.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecutionMode {

    @JsonProperty("TYPE")
     private String TYPE;
    @JsonProperty("ORIENTATION")
     private String ORIENTATION;

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getORIENTATION() {
        return ORIENTATION;
    }

    public void setORIENTATION(String ORIENTATION) {
        this.ORIENTATION = ORIENTATION;
    }
}
