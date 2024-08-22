package org.selenium.pom.constants;

public enum TaxRate {
    CALIFORNIA(0.075),
    MISSOURI(0.0423),
    ALABAMA(0.040);

    public Double rate;

    TaxRate(Double rate){
        this.rate = rate;
    }
}
