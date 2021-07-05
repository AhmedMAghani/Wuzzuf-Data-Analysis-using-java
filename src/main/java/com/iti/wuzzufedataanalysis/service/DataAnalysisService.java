package com.iti.wuzzufedataanalysis.service;

import com.iti.wuzzufedataanalysis.entity.GroupByCount;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;

import java.util.List;

public interface DataAnalysisService {
    Dataset<GroupByCount> getCompanyCount();
    StructType getDatasetSchema();
    List<GroupByCount> getSkills();
    Dataset<GroupByCount> getJobCount();
    Dataset<GroupByCount> getLocationCount();
}
