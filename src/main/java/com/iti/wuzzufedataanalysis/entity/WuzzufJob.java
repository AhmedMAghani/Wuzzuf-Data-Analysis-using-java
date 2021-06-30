package com.iti.wuzzufedataanalysis.entity;

import java.util.ArrayList;
import java.util.Arrays;

public class WuzzufJob {
    private String jobTitle;
    private String companyName;
    private String jobLocation;
    private String jobType;
    private String jobLevel;
    private Double jobMiniYearExp;
    private Double jobMaxYearExp;
    private String jobCountry;
    private ArrayList<String> Skills;

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

    public Double getJobMiniYearExp() {
        return jobMiniYearExp;
    }

    public void setJobMiniYearExp(Double jobMiniYearExp) {
        this.jobMiniYearExp = jobMiniYearExp;
    }

    public Double getJobMaxYearExp() {
        return jobMaxYearExp;
    }

    public void setJobMaxYearExp(Double jobMaxYearExp) {
        this.jobMaxYearExp = jobMaxYearExp;
    }

    public String getJobCountry() {
        return jobCountry;
    }

    public void setJobCountry(String jobCountry) {
        this.jobCountry = jobCountry;
    }

    public ArrayList<String> getSkills() {
        return Skills;
    }

    public void setSkills(String[] skills) {
        Skills = new ArrayList(Arrays.asList(skills));
    }
}
