package com.iti.wuzzufedataanalysis.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class WuzzufJob implements Serializable {
    private String jobTitle;
    private String companyName;
    private String jobLocation;
    private String jobType;
    private String jobLevel;
    private String jopExpYearSt;
//    private Double jobMiniYearExp;
//    private Double jobMaxYearExp;
    private String jobCountry;
    private ArrayList<String> skills;
//    private String skills;

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

    public String getJopExpYearSt() {
        return jopExpYearSt;
    }

    public void setJopExpYearSt(String jopExpYearSt) {
        this.jopExpYearSt = jopExpYearSt;
    }

//    public void setSkills(ArrayList<String> skills) {
//        Skills = skills;
//    }

//    public Double getJobMiniYearExp() {
//        return jobMiniYearExp;
//    }

//    public void setJobMiniYearExp(Double jobMiniYearExp) {
//        this.jobMiniYearExp = jobMiniYearExp;
//    }

//    public Double getJobMaxYearExp() {
//        return jobMaxYearExp;
//    }

//    public void setJobMaxYearExp(Double jobMaxYearExp) {
//        this.jobMaxYearExp = jobMaxYearExp;
//    }

    public String getJobCountry() {
        return jobCountry;
    }

    public void setJobCountry(String jobCountry) {
        this.jobCountry = jobCountry;
    }

//    public String getSkills() {
//        return skills;
//    }

//    public void setSkills(String skills) {
//        this.skills = skills;
//    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }
}
