/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stfc.utils.FormatMessage;

/**
 *
 * @author viettx
 */
public class BaseResponse {

    private static final Logger mLog = LoggerFactory.getLogger(BaseResponse.class);
    private String mid;
    private String code;
    private String desc;
    private Integer total;
    private Object rows;
    private transient String lang;

    public static BaseResponse parse(String inputMsg, FormatMessage formatMessage) {
        String[] arr = inputMsg.split("\\-", 3);
        if (arr == null || arr.length < 3) {
            mLog.info("wrong format errorcode: " + inputMsg + " arrLenth: " + arr.length);
            return null;
        }
        BaseResponse res = new BaseResponse();
        res.setCode(arr[2]); // it 's normal
        res.setDesc(formatMessage.getMessage(arr[2]));
        return res;
    }

    public static BaseResponse parse(String inputMsg, FormatMessage formatMessage, String language) {
        String[] arr = inputMsg.split("\\-", 3);
        if (arr == null || arr.length < 3) {
            mLog.info("wrong format errorcode: " + inputMsg + " arrLenth: " + arr.length);
            return null;
        }
        BaseResponse res = new BaseResponse();
        res.setCode(arr[2]); // it 's normal
        res.setDesc(formatMessage.getMessage(arr[2], language));
        return res;
    }

    /**
     * @return the mid
     */
    public String getMid() {
        return mid;
    }

    /**
     * @param mid the mid to set
     */
    public void setMid(String mid) {
        this.mid = mid;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

}
