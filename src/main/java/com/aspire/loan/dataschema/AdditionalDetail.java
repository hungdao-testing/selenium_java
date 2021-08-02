package com.aspire.loan.dataschema;

import java.util.List;

public class AdditionalDetail {
    private String country;

    private List<String> solutions;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        return "AdditionalDetail{" +
                "country='" + country + '\'' +
                ", solutions=" + solutions +
                '}';
    }

    public void setSolutions(List<String> solutions) {
        this.solutions = solutions;
    }

    public List<String> getSolutions() {
        return this.solutions;
    }
}
