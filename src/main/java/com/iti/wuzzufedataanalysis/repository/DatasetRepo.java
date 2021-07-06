package com.iti.wuzzufedataanalysis.repository;

import com.iti.wuzzufedataanalysis.entity.WuzzufDataModel;
import com.iti.wuzzufedataanalysis.entity.WuzzufJob;
import org.apache.spark.sql.Dataset;

import java.util.ArrayList;
import java.util.List;

public interface DatasetRepo {
    Dataset<WuzzufJob> getWuzzufJobsDataset();
    Dataset<WuzzufDataModel> groupDatasetByCompanyName();
    List<ArrayList<String>> filterSkills();
    Dataset<WuzzufDataModel> groupDatasetByJobLocation();
    Dataset<WuzzufDataModel> groupDatasetByJobTitle();
}
