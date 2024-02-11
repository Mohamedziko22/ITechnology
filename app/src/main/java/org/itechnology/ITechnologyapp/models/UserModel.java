package org.itechnology.ITechnologyapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserModel implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name_ar")
    private String nameAR;
    @SerializedName("name_en")
    private String nameEN;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private String phone;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAR() {
        return nameAR;
    }

    public void setNameAR(String nameAR) {
        this.nameAR = nameAR;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
