/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author dong.dv
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class STFCResponse implements Serializable {

    @JsonProperty("code")
    private String returnCode;
    @JsonProperty("desc")
    private String returnMessage;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("rows")
    private Object result;

    public STFCResponse() {
        this.returnCode = "200";
        this.returnMessage = "Success";
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
