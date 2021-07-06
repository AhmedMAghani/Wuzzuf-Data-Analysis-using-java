package com.iti.wuzzufedataanalysis.service;

import com.iti.wuzzufedataanalysis.entity.WuzzufDataModel;
import com.iti.wuzzufedataanalysis.entity.WuzzufJob;
import com.iti.wuzzufedataanalysis.repository.DatasetRepo;
import org.apache.spark.ml.clustering.KMeans;
import org.apache.spark.ml.clustering.KMeansModel;
import org.apache.spark.ml.evaluation.ClusteringEvaluator;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;
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

    public List<WuzzufJob> getJobs(){
        return datasetRepo.getWuzzufJobsDataset().collectAsList();
    }

    private void dropNullValues(){
        datasetRepo.getWuzzufJobsDataset().drop().na();
    }

    private void dropDuplication(){
        datasetRepo.getWuzzufJobsDataset().dropDuplicates();
    }

//    TODO: K-Means Algorithm
//    public Dataset<Row> prepareDatasetForKMeans(String colName){
//        String kMeansColName = "features";
//        return datasetRepo.getWuzzufJobsDataset().withColumn(kMeansColName, functions.col(colName)).orderBy().select(kMeansColName);
//    }
//
//    public void kMeans(String colName){
//        KMeans kmeans = new KMeans().setK(2).setSeed(1L);
//        KMeansModel model = kmeans.fit(prepareDatasetForKMeans(colName));
//
//        Dataset<Row> predictions = model.transform(prepareDatasetForKMeans(colName));
//
//        ClusteringEvaluator evaluator = new ClusteringEvaluator();
//
//        double silhouette = evaluator.evaluate(predictions);
//        System.out.println("Silhouette with squared euclidean distance = " + silhouette);
//
//        Vector[] centers = model.clusterCenters();
//        System.out.println("Cluster Centers: ");
//        for (Vector center: centers) {
//            System.out.println(center);
//        }
//    }
}
