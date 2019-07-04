package com.ebricks.script.assertion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UDAResponseResult {

    @JsonProperty("success")
    private boolean success;
    @JsonProperty("status")
    private String status;
    @JsonProperty("value")
    private String value;//": "This should equal 'Aman'",
    @JsonProperty("base")
    private UDAElement base = new UDAElement();
    @JsonProperty("result")
    private UDAElement result = new UDAElement();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UDAElement getBase() {
        return base;
    }

    public void setBase(UDAElement base) {
        this.base = base;
    }

    public UDAElement getResult() {
        return result;
    }

    public void setResult(UDAElement result) {
        this.result = result;
    }
}
