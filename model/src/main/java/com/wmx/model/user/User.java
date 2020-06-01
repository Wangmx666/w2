package com.wmx.model.user;

import com.wmx.common.PageUtil;

public class User extends PageUtil{

    private int userID;

    private String userAccount;

    private String userPwd;

    private String userImgCode;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserImgCode() {
        return userImgCode;
    }

    public void setUserImgCode(String userImgCode) {
        this.userImgCode = userImgCode;
    }
}
