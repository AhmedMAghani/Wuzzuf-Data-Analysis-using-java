package com.iti.wuzzufedataanalysis.controller;

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

@RestController
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestMapping("wuzzufDataAnalysis/api")
public class DataAnalysisController {
    // Ahmed
    private final DataAnalysisService dataAnalysisService;

    @Autowired
    public DataAnalysisController(DataAnalysisService dataAnalysisService){
        this.dataAnalysisService = dataAnalysisService;
    }

    @GetMapping("/schema")
    public ResponseEntity<StructType> getDatasetSchema(){
        return new ResponseEntity<StructType>(dataAnalysisService.getDatasetSchema(), HttpStatus.OK);
    }
    // Mahmoud
    // Ibrahim
}
