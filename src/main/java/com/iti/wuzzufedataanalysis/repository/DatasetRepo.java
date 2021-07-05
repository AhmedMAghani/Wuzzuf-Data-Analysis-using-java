package com.iti.wuzzufedataanalysis.repository;

import com.iti.wuzzufedataanalysis.entity.GroupByCount;
import com.iti.wuzzufedataanalysis.entity.WuzzufJob;
import org.apache.spark.sql.Dataset;

import java.util.ArrayList;
import java.util.List;

public interface DatasetRepo {
    Dataset<WuzzufJob> getWuzzufJobsDataset();
    Dataset<GroupByCount> groupDatasetByCompanyName();
    List<ArrayList<String>> filterSkills();
    Dataset<GroupByCount> groupDatasetByJobLocation();
    Dataset<GroupByCount> groupDatasetByJobTitle();
}
