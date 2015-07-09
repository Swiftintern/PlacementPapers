package models;

import com.orm.SugarRecord;

/**
 * Created by chhavi on 9/7/15.
 */
public class SavedExperience extends SugarRecord<SavedExperience> {
    String text;
    String title;

    public SavedExperience(String text, String title, String paper_id) {
        this.text = text;
        this.title = title;
        this.paper_id = paper_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public SavedExperience(String text, String id) {
        this.text = text;
        this.paper_id = id;
    }

    String paper_id;

    public SavedExperience() {
    }




}
