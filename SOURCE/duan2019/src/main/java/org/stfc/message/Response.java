/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.message;

import org.json.JSONObject;

/**
 *
 * @author quangtt
 */
public class Response {

    public Response(String e, String m) {
        this.e = e;
        this.m = m;
        this.timestamp = System.currentTimeMillis();
    }

    private String e;
    private String m;
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the e
     */
    public String getE() {
        return e;
    }

    /**
     * @param e the e to set
     */
    public void setE(String e) {
        this.e = e;
    }

    /**
     * @return the m
     */
    public String getM() {
        return m;
    }

    /**
     * @param m the m to set
     */
    public void setM(String m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return new JSONObject(this).toString();
    }

}
