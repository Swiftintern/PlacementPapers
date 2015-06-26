package models;

import java.util.List;

/**
 * Created by chhavi on 24/6/15.
 */
public class PapersResponse {

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }



    private Organization organization;

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    private List<Experience> experiences;
}
