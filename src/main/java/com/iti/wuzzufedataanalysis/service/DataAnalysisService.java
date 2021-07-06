package com.iti.wuzzufedataanalysis.service;

import com.iti.wuzzufedataanalysis.entity.WuzzufDataModel;
import com.iti.wuzzufedataanalysis.entity.WuzzufJob;
import org.apache.spark.sql.types.StructType;

import java.util.List;

public interface DataAnalysisService {
    List<WuzzufDataModel> getCompanyCount();
    StructType getDatasetSchema();
    List<WuzzufDataModel> getSkills();
    List<WuzzufDataModel> getJobCount();
    List<WuzzufDataModel> getLocationCount();
    List<WuzzufJob> getJobs();
//    void kMeans(String colName);
}
