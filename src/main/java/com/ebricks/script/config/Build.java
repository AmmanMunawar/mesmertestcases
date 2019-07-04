package com.ebricks.script.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Build {

    @JsonProperty("FILE_NAME")
    private String FILE_NAME;
    @JsonProperty("NAME")
    private String NAME;
    @JsonProperty("PACKAGE")
    private String PACKAGE;
    @JsonProperty("VERSION")
    private String VERSION;
    @JsonProperty("VERSION_LONG")
    private String VERSION_LONG;
    @JsonProperty("UNINSTALL_BUILD")
    private String UNINSTALL_BUILD;

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPACKAGE() {
        return PACKAGE;
    }

    public void setPACKAGE(String PACKAGE) {
        this.PACKAGE = PACKAGE;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getVERSION_LONG() {
        return VERSION_LONG;
    }

    public void setVERSION_LONG(String VERSION_LONG) {
        this.VERSION_LONG = VERSION_LONG;
    }

    public String getUNINSTALL_BUILD() {
        return UNINSTALL_BUILD;
    }

    public void setUNINSTALL_BUILD(String UNINSTALL_BUILD) {
        this.UNINSTALL_BUILD = UNINSTALL_BUILD;
    }
}
