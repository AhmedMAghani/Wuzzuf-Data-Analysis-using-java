package com.iti.wuzzufedataanalysis.repository;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public interface DatasetRepo {
    // Ahmed
    Dataset<Row> getWuzzufJobsDataset();
    // Mahmoud
    // Ibrahim
}
