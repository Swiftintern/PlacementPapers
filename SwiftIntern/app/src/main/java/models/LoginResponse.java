package models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chhavi on 11/7/15.
 */
public class LoginResponse {
    boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;


    }

