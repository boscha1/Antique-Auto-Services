package org.antiqueauto.services.domain;

import java.util.Date;

public class BillingInfo {
    private Integer id;
    private Double hourlyRate;
    private Double materialsPercentage;
    private Double insuranceRate;
    private Date firstInvoice;
    private Boolean firstInvoiceMailed;
    private Date secondInvoice;
    private Boolean secondInvoiceMailed;

    public BillingInfo(Double hourlyRate,
                       Double materialsPercentage,
                       Double insuranceRate,
                       Date firstInvoice,
                       Date secondInvoice)
    {
        this.hourlyRate = hourlyRate;
        this.materialsPercentage = materialsPercentage;
        this.insuranceRate = insuranceRate;
        this.firstInvoice = firstInvoice;
        this.firstInvoiceMailed = false;
        this.secondInvoice = secondInvoice;
        this.secondInvoiceMailed = false;
    }

    public BillingInfo(Integer id,
                       Double hourlyRate,
                       Double materialsPercentage,
                       Double insuranceRate,
                       Date firstInvoice,
                       Boolean firstInvoiceMailed,
                       Date secondInvoice,
                       Boolean secondInvoiceMailed) {
        this.id = id;
        this.hourlyRate = hourlyRate;
        this.materialsPercentage = materialsPercentage;
        this.insuranceRate = insuranceRate;
        this.firstInvoice = firstInvoice;
        this.firstInvoiceMailed = firstInvoiceMailed;
        this.secondInvoice = secondInvoice;
        this.secondInvoiceMailed = secondInvoiceMailed;
    }

    public Integer getId() {
        return id;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Double getMaterialsPercentage() {
        return materialsPercentage;
    }

    public void setMaterialsPercentage(Double materialsPercentage) {
        this.materialsPercentage = materialsPercentage;
    }

    public Double getInsuranceRate() {
        return insuranceRate;
    }

    public void setInsuranceRate(Double insuranceRate) {
        this.insuranceRate = insuranceRate;
    }

    public Date getFirstInvoice() {
        return firstInvoice;
    }

    public void setFirstInvoice(Date firstInvoice) {
        this.firstInvoice = firstInvoice;
    }

    public Boolean getFirstInvoiceMailed() {
        return firstInvoiceMailed;
    }

    public void setFirstInvoiceMailed(Boolean firstInvoiceMailed) {
        this.firstInvoiceMailed = firstInvoiceMailed;
    }

    public Date getSecondInvoice() {
        return secondInvoice;
    }

    public void setSecondInvoice(Date secondInvoice) {
        this.secondInvoice = secondInvoice;
    }

    public Boolean getSecondInvoiceMailed() {
        return secondInvoiceMailed;
    }

    public void setSecondInvoiceMailed(Boolean secondInvoiceMailed) {
        this.secondInvoiceMailed = secondInvoiceMailed;
    }
}
