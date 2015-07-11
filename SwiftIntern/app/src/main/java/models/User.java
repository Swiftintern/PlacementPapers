package models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chhavi on 11/7/15.
 */
public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("_name")
    private String name;

    @SerializedName("_email")
    private String email;


    @SerializedName("_id")
    private String id;


}
