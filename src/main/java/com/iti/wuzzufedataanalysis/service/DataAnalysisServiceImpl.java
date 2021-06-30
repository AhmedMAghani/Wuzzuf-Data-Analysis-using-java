package com.iti.wuzzufedataanalysis.service;

import com.iti.wuzzufedataanalysis.repository.DatasetRepo;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataAnalysisServiceImpl implements DataAnalysisService {

    private final DatasetRepo datasetRepo;

    @Autowired
    public DataAnalysisServiceImpl(DatasetRepo datasetRepo){
        this.datasetRepo = datasetRepo;
        dropNullValues();
        dropDuplication();
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
