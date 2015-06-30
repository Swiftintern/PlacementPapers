package models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chhavi on 19/6/15.
 */
public class Organization {

    @SerializedName("_photo_id")
    private String photoId;

    @SerializedName("_name")
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage(){
       String BASE_URL = "http://swiftintern.com/organizations/photo/";
        String url = BASE_URL + id;

        return url;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    @SerializedName("_id")
    private String id;





}
