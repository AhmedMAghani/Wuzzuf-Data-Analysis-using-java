package com.iti.wuzzufedataanalysis.service;

import com.iti.wuzzufedataanalysis.entity.WuzzufDataModel;
import com.iti.wuzzufedataanalysis.repository.DatasetRepo;
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

    public List<WuzzufDataModel> getCompanyCount(){
        return datasetRepo.groupDatasetByCompanyName().collectAsList().stream().sorted(Comparator.comparingLong(WuzzufDataModel::getCount).reversed()).skip(1).collect(Collectors.toList());
    }

    public List<WuzzufDataModel> getJobCount(){
        return datasetRepo.groupDatasetByJobTitle().collectAsList().stream().sorted(Comparator.comparingLong(WuzzufDataModel::getCount).reversed()).collect(Collectors.toList());
    }

    public List<WuzzufDataModel> getLocationCount(){
        return datasetRepo.groupDatasetByJobLocation().collectAsList().stream().sorted(Comparator.comparingLong(WuzzufDataModel::getCount).reversed()).collect(Collectors.toList());
    }

    public List<WuzzufDataModel> getSkills(){
        return datasetRepo.filterSkills().stream().flatMap(Collection::stream).collect(Collectors.groupingBy(skill->skill,Collectors.counting())).entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(key -> new WuzzufDataModel(key.getKey(), key.getValue())).collect(Collectors.toList());
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
