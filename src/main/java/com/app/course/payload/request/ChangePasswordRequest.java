package com.app.course.payload.request;

import lombok.AllArgsConstructor;

import java.io.Serializable;

public class ChangePasswordRequest implements Serializable {
    private long id;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequest(long id, String oldPassword, String newPassword) {
        this.id = id;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangePasswordRequest(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
