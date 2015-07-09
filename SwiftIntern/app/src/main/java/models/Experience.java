package models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chhavi on 24/6/15.
 */
public class Experience {
    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @SerializedName("_organization_id")
    private int organisationId;

    @SerializedName("_user_id")
    private int userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @SerializedName("_id")
    private String id;

    @SerializedName("_title")
    private String title;

    @SerializedName("_details")
    private String details;



}
