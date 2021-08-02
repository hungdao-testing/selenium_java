package com.aspire.loan.dataschema;

public class BusinessDetailEdit {
    private String activity;

    private String detailActivity;

    private String liveWebsite;

    private String numberOfPeople;

    private String annualTurnOver;

    @Override
    public String toString() {
        return "BusinessDetailEdit{" +
                "activity='" + activity + '\'' +
                ", detailActivity='" + detailActivity + '\'' +
                ", liveWebsite='" + liveWebsite + '\'' +
                ", numberOfPeople='" + numberOfPeople + '\'' +
                ", annualTurnOver='" + annualTurnOver + '\'' +
                '}';
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return this.activity;
    }

    public void setDetailActivity(String detailActivity) {
        this.detailActivity = detailActivity;
    }

    public String getDetailActivity() {
        return this.detailActivity;
    }

    public void setLiveWebsite(String liveWebsite) {
        this.liveWebsite = liveWebsite;
    }

    public String getLiveWebsite() {
        return this.liveWebsite;
    }

    public void setNumberOfPeople(String numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public void setAnnualTurnOver(String annualTurnOver) {
        this.annualTurnOver = annualTurnOver;
    }

    public String getAnnualTurnOver() {
        return this.annualTurnOver;
    }
}