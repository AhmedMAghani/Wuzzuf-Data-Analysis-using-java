package com.iti.wuzzufedataanalysis.repository;

import com.iti.wuzzufedataanalysis.entity.GroupByCount;
import com.iti.wuzzufedataanalysis.entity.WuzzufJob;
import org.apache.spark.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private final Dataset<WuzzufJob> wuzzufJobsDataset;

    public Dataset<WuzzufJob> getWuzzufJobsDataset() {
        return wuzzufJobsDataset;
    }

    @Autowired
    public DatasetRepoImpl(SparkSession spark) {
        this.spark = spark;
        Dataset<Row> wuzzufRowDataset = readDataSet();
        wuzzufRowDataset = factorizeSkillsIntoArray(wuzzufRowDataset);
        wuzzufJobsDataset = convertDatasetToTypedDataset(wuzzufRowDataset);
    }

    public Dataset<GroupByCount> groupDatasetByCompanyName(){
        return wuzzufJobsDataset.withColumn("countedCol",functions.column("jobTitle")).groupBy("countedCol").count().as(Encoders.bean(GroupByCount.class));
    }

    public Dataset<GroupByCount> groupDatasetByJobTitle(){
        return wuzzufJobsDataset.withColumn("countedCol",functions.column("companyName")).groupBy("countedCol").count().as(Encoders.bean(GroupByCount.class));
    }

    public Dataset<GroupByCount> groupDatasetByJobLocation(){
        return wuzzufJobsDataset.withColumn("countedCol",functions.column("jobLocation")).groupBy("countedCol").count().as(Encoders.bean(GroupByCount.class));
    }

    public List<ArrayList<String>> filterSkills(){
        return wuzzufJobsDataset.collectAsList().stream().map(WuzzufJob::getSkills).collect(Collectors.toList());
    }

    public void cleanTempCol(){
        wuzzufJobsDataset.drop("countedCol").as(Encoders.bean(WuzzufJob.class));
    }

    private Dataset<Row> readDataSet(){
        String WUZZUF_DATASET_CSV = "C:\\Users\\light\\IdeaProjects\\WuzzufDataAnalysis\\src\\main\\resources\\Wuzzuf_Jobs.csv";
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

//    TODO: complete the factorization method
//    public void factorizeExpYears(){
//        wuzzufJobsDataset = wuzzufJobsDataset.withColumn("jobMiniYearExp",functions.regexp_extract(wuzzufJobsDataset.col("jopExpYearSt"),"([0-9]+)|(null)",0));
//        wuzzufJobsDataset.select("skills").map((String)value -> {
//            Pattern r = Pattern.compile("\"[a-zA-Z]+,");
//            Matcher m = r.matcher((String)value);
//        })
//        wuzzufJobsDataset.show();
//    }
}
