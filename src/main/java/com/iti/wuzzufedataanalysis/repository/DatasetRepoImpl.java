package com.iti.wuzzufedataanalysis.repository;

import com.iti.wuzzufedataanalysis.entity.WuzzufDataModel;
import com.iti.wuzzufedataanalysis.entity.WuzzufJob;
import org.apache.spark.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Repository
@Scope("singleton")
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

    private Dataset<WuzzufJob> wuzzufJobsDataset;

    public Dataset<WuzzufJob> getWuzzufJobsDataset() {
        return wuzzufJobsDataset;
    }

    @Autowired
    public DatasetRepoImpl(SparkSession spark) {
        this.spark = spark;
        Dataset<Row> wuzzufRowDataset = readDataSet();
        wuzzufRowDataset = factorizeSkillsIntoArray(wuzzufRowDataset);
        wuzzufRowDataset = factorizeExpYearsColumn(wuzzufRowDataset);
        wuzzufJobsDataset = convertDatasetToTypedDataset(wuzzufRowDataset);
        deleteCol("jopExpYearSt");
    }

    public Dataset<WuzzufDataModel> groupDatasetByCompanyName(){
        return wuzzufJobsDataset.withColumn("countedCol",functions.column("companyName")).groupBy("countedCol").count().as(Encoders.bean(WuzzufDataModel.class));
    }

    public Dataset<WuzzufDataModel> groupDatasetByJobTitle(){
        return wuzzufJobsDataset.withColumn("countedCol",functions.column("jobTitle")).groupBy("countedCol").count().as(Encoders.bean(WuzzufDataModel.class));
    }

    public Dataset<WuzzufDataModel> groupDatasetByJobLocation(){
        return wuzzufJobsDataset.withColumn("countedCol",functions.column("jobLocation")).groupBy("countedCol").count().as(Encoders.bean(WuzzufDataModel.class));
    }

    public List<ArrayList<String>> filterSkills(){
        return wuzzufJobsDataset.collectAsList().stream().map(WuzzufJob::getSkills).collect(Collectors.toList());
    }

    public void deleteCol(String columnName){
        wuzzufJobsDataset.drop(columnName).as(Encoders.bean(WuzzufJob.class));
    }

    public void saveDataset(Dataset<Row> newDataset){
        wuzzufJobsDataset = newDataset.as(Encoders.bean(WuzzufJob.class));
    }

    private Dataset<Row> readDataSet(){
        String WUZZUF_DATASET_CSV = "C:\\Users\\light\\IdeaProjects\\WuzzufDataAnalysis\\src\\main\\resources\\static\\Wuzzuf_Jobs.csv";
        String DATASET_EXTENSION = "csv";
        return spark.read().format(DATASET_EXTENSION)
                .option(SEP,CSV_CONF.get(SEP))
                .option(INFERSCHEMA,CSV_CONF.get(INFERSCHEMA))
                .option(HEADER,CSV_CONF.get(HEADER))
                .load(WUZZUF_DATASET_CSV);
    }

    private Dataset<Row> factorizeSkillsIntoArray(Dataset<Row> wuzzufRowDataset){
       return wuzzufRowDataset.withColumn("skills", functions.split(functions.column("skills"), ","));
    }

    private Dataset<WuzzufJob> convertDatasetToTypedDataset(Dataset<Row> wuzzufRowDataset){
        return wuzzufRowDataset.as(Encoders.bean(WuzzufJob.class));
    }

    private Dataset<Row> factorizeExpYearsColumn(Dataset<Row> wuzzufRowDataset){
        Dataset<Row> tempDataset;
        String miniYearExpExtractionPattern = "(\\d+)|(null)";
        String miniYearExpReplacementPattern = "((\\d+)?-)|(null)|((\\d+)?\\+)";
        String maxYearExpExtractionPattern = "(\\d+)|(\\s{1,2})";
        String nullValuePattern = "(null)|(\\s{1,2})";
        tempDataset = wuzzufRowDataset.withColumn("jobMiniYearExp", functions.regexp_extract(functions.col("jopExpYearSt"),miniYearExpExtractionPattern,0));
        tempDataset = tempDataset.withColumn("jopExpYearSt", functions.regexp_replace(functions.col("jopExpYearSt"), miniYearExpReplacementPattern, ""));
        tempDataset = tempDataset.withColumn("jobMaxYearExp", functions.regexp_extract(functions.col("jopExpYearSt"), maxYearExpExtractionPattern, 0));
        tempDataset = tempDataset.withColumn("jobMiniYearExp", functions.regexp_replace(functions.col("jobMiniYearExp"), nullValuePattern, String.valueOf(0)).cast("double"));
        tempDataset = tempDataset.withColumn("jobMaxYearExp", functions.regexp_replace(functions.col("jobMaxYearExp"), nullValuePattern, String.valueOf(0)).cast("double"));
        return tempDataset;
    }
}
