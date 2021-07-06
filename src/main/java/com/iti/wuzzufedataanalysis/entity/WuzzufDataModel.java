package com.iti.wuzzufedataanalysis.entity;

import java.io.Serializable;

public class WuzzufDataModel implements Serializable {
    private String countedCol;
    private long count;

    public WuzzufDataModel(){}

    public WuzzufDataModel(String countedCol, long count) {
        this.countedCol = countedCol;
        this.count = count;
    }

    public String getCountedCol() {
        return countedCol;
    }

    public void setCountedCol(String countedCol) {
        this.countedCol = countedCol;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
