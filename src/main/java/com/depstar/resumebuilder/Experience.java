package com.depstar.resumebuilder;

public class Experience {
    String organisation;
    String designation;

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDuration() {
        return duration;
    }

    public Experience(String organisation, String designation, String duration) {
        this.organisation = organisation;
        this.designation = designation;
        this.duration = duration;
    }

    String duration;
}
