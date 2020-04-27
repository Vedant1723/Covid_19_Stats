package com.vedev.covid_19sample;

public class Country {

    String countryName,countryFlag,totalCases,totalDeaths,todayCases,todayDeaths,activeCases,recovered;

    public Country(String countryName, String countryFlag, String totalCases, String totalDeaths, String todayCases, String todayDeaths, String activeCases, String recovered) {
        this.countryName = countryName;
        this.countryFlag = countryFlag;
        this.totalCases = totalCases;
        this.totalDeaths = totalDeaths;
        this.todayCases = todayCases;
        this.todayDeaths = todayDeaths;
        this.activeCases = activeCases;
        this.recovered = recovered;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(String activeCases) {
        this.activeCases = activeCases;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}
