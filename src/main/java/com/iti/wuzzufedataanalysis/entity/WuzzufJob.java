package com.iti.wuzzufedataanalysis.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class WuzzufJob implements Serializable {
    private String jobTitle;
    private String companyName;
    private String jobLocation;
    private String jobType;
    private String jobLevel;
    private String jobCountry;
    private ArrayList<String> skills;
    private double jobMiniYearExp;
    private double jobMaxYearExp;

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getJobCountry() {
        return jobCountry;
    }

    public void setJobCountry(String jobCountry) {
        this.jobCountry = jobCountry;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public double getJobMiniYearExp() {
        return jobMiniYearExp;
    }

    public void setJobMiniYearExp(double jobMiniYearExp) {
        this.jobMiniYearExp = jobMiniYearExp;
    }

    public double getJobMaxYearExp() {
        return jobMaxYearExp;
    }

    public void setJobMaxYearExp(double jobMaxYearExp) {
        this.jobMaxYearExp = jobMaxYearExp;
    }
}
