package org.itechnology.ITechnologyapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoriesResponseModel implements Serializable {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String msg;
    @SerializedName("output")
    private List<CategoryModel> output;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CategoryModel> getOutput() {
        return output;
    }

    public void setOutput(List<CategoryModel> output) {
        this.output = output;
    }
}
