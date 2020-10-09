package com.example.golfscorecard;

import java.io.Serializable;

public class Hole implements Serializable {
    private int strokeCount;
    private String label;

    public Hole(String label, int strokeCount){
        this.strokeCount = strokeCount;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public int getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
    }

}
