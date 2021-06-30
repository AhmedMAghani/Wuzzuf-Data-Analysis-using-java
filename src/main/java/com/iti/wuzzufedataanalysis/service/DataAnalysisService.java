package com.iti.wuzzufedataanalysis.service;

import org.apache.spark.sql.types.StructType;

public interface DataAnalysisService {
    public StructType getDatasetSchema();
}
