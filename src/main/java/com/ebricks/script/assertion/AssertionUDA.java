package com.ebricks.script.assertion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssertionUDA {

    @JsonProperty("node")
    private int node;
    @JsonProperty("udaId")
    private int udaId;
//    @JsonProperty("typeInfo")
//    private String typeInfo;
    @JsonProperty("name")
    private String name;
    @JsonProperty("_id")
    private String _id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("elementType")
    private String elementType;
    @JsonProperty("value")
    private String value;
    @JsonProperty("dynamicVars")
    private List<DynamicVariable> dynamicVars = new ArrayList<>();
    @JsonProperty("title")
    private String title;
    @JsonProperty("activity")
    private String activity;
    @JsonProperty("result")
    private UDAResponseResult result = new UDAResponseResult();

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public int getUdaId() {
        return udaId;
    }

    public void setUdaId(int udaId) {
        this.udaId = udaId;
    }

//    public String getTypeInfo() {
//        return typeInfo;
//    }
//
//    public void setTypeInfo(String typeInfo) {
//        this.typeInfo = typeInfo;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    public String getDynamicVars() {
//        return dynamicVars;
//    }
//
//    public void setDynamicVars(String dynamicVars) {
//        this.dynamicVars = dynamicVars;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public UDAResponseResult getResult() {
        return result;
    }

    public void setResult(UDAResponseResult result) {
        this.result = result;
    }

    public List<DynamicVariable> getDynamicVars() {
        return dynamicVars;
    }

    public void setDynamicVars(List<DynamicVariable> dynamicVars) {
        this.dynamicVars = dynamicVars;
    }
}
