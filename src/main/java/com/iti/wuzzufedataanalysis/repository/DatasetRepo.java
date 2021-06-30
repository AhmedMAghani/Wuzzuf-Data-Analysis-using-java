package com.iti.wuzzufedataanalysis.repository;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public interface DatasetRepo {
    public Dataset<Row> getWuzzufJobsDataset();
}
