package com.iti.wuzzufedataanalysis.controller;

import com.iti.wuzzufedataanalysis.entity.WuzzufDataModel;
import com.iti.wuzzufedataanalysis.service.DataAnalysisService;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestMapping("wuzzufDataAnalysis/api")
public class DataAnalysisController {
    private final DataAnalysisService dataAnalysisService;

    @Autowired
    public DataAnalysisController(DataAnalysisService dataAnalysisService){
        this.dataAnalysisService = dataAnalysisService;
    }

    @GetMapping("/schema")
    public ResponseEntity<StructType> getDatasetSchema(){
        return new ResponseEntity<StructType>(dataAnalysisService.getDatasetSchema(), HttpStatus.OK);
    }

    @GetMapping("/getCompanysCount")
    public ResponseEntity<List<WuzzufDataModel>> getCompanyCount(){
        return new ResponseEntity<>(dataAnalysisService.getCompanyCount(), HttpStatus.OK);
    }

    @GetMapping("/getJobsCount")
    public ResponseEntity<List<WuzzufDataModel>> getJobCount(){
        return new ResponseEntity<>(dataAnalysisService.getJobCount(), HttpStatus.OK);
    }

    @GetMapping("/getLocationsCount")
    public ResponseEntity<List<WuzzufDataModel>> getLocationCount(){
        return new ResponseEntity<>(dataAnalysisService.getLocationCount(), HttpStatus.OK);
    }

    @GetMapping("/getSkills")
    public ResponseEntity<List<WuzzufDataModel>> getSkills(){
        return new ResponseEntity<>(dataAnalysisService.getSkills(), HttpStatus.OK);
    }
}
