package org.itechnology.ITechnologyapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserResponseModel implements Serializable {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String msg;
    @SerializedName("output")
    private UserModel output;

    public UserResponseModel(boolean status, String msg, UserModel output) {
        this.status = status;
        this.msg = msg;
        this.output = output;
    }

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

    public UserModel getOutput() {
        return output;
    }

    public void setOutput(UserModel output) {
        this.output = output;
    }
}
