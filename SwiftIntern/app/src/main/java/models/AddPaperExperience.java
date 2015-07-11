package models;

/**
 * Created by chhavi on 11/7/15.
 */
public class AddPaperExperience {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    private boolean success;
    private Experience experience;

}
