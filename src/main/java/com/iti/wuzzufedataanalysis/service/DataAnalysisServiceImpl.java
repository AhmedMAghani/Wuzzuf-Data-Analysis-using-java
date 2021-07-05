package com.iti.wuzzufedataanalysis.service;

import com.iti.wuzzufedataanalysis.entity.GroupByCount;
import com.iti.wuzzufedataanalysis.repository.DatasetRepo;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {
    private final DatasetRepo datasetRepo;

    @Autowired
    public DataAnalysisServiceImpl(DatasetRepo datasetRepo){
        this.datasetRepo = datasetRepo;
        dropNullValues();
        dropDuplication();
    }

    public Dataset<GroupByCount> getCompanyCount(){
        return datasetRepo.groupDatasetByCompanyName();
    }

    public Dataset<GroupByCount> getJobCount(){
        return datasetRepo.groupDatasetByJobTitle();
    }

    public Dataset<GroupByCount> getLocationCount(){
        return datasetRepo.groupDatasetByJobLocation();
    }

    public List<GroupByCount> getSkills(){
        return datasetRepo.filterSkills().stream().flatMap(Collection::stream).collect(Collectors.groupingBy(skill->skill,Collectors.counting())).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(key -> new GroupByCount(key.getKey(), key.getValue())).collect(Collectors.toList());
    }

    public StructType getDatasetSchema(){
        return datasetRepo.getWuzzufJobsDataset().schema();
    }

    private void dropNullValues(){
        datasetRepo.getWuzzufJobsDataset().drop().na();
    }

    private void dropDuplication(){
        datasetRepo.getWuzzufJobsDataset().dropDuplicates();
    }
}
