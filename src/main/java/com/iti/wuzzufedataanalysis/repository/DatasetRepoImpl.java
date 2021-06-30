package com.iti.wuzzufedataanalysis.repository;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DatasetRepoImpl implements DatasetRepo {
    private final String SEP = "sep";
    private final String INFERSCHEMA = "inferSchema";
    private final String HEADER = "header";
    private final HashMap<String,String> CSV_CONF = new HashMap<String,String>(){
        {
            put(SEP,",");
            put(INFERSCHEMA,"true");
            put(HEADER,"true");
        }
    };

    private final SparkSession spark;

    private Dataset<Row> wuzzufJobsDataset;

    public Dataset<Row> getWuzzufJobsDataset() {
        return wuzzufJobsDataset;
    }

    @Autowired
    public DatasetRepoImpl(SparkSession spark) {
        this.spark = spark;
        readDataSet();
    }

    private void readDataSet(){
        String WUZZUF_DATASET_CSV = "C:\\Users\\light\\IdeaProjects\\WuzzufDataAnalysis\\src\\main\\resources\\Wuzzuf_Jobs.csv";
        String DATASET_EXTENSION = "csv";
        wuzzufJobsDataset = spark.read().format(DATASET_EXTENSION)
                .option(SEP,CSV_CONF.get(SEP))
                .option(INFERSCHEMA,CSV_CONF.get(INFERSCHEMA))
                .option(HEADER,CSV_CONF.get(HEADER))
                .load(WUZZUF_DATASET_CSV);
    }
}
