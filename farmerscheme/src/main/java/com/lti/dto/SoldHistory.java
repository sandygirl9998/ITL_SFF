package com.lti.dto;

import java.util.Date;

public class SoldHistory {
	  private Date Date;
	    private String cropName;
	    private int Quantity;
	    private double MSP;
	    private double soldPrice;
//	    private double totalPrice;
	    public java.util.Date getDate() {
	        return Date;
	    }
	    public void setDate(java.util.Date date) {
	        Date = date;
	    }
	    public String getCropName() {
	        return cropName;
	    }
	    public void setCropName(String cropName) {
	        this.cropName = cropName;
	    }
	    public int getQuantity() {
	        return Quantity;
	    }
	    public void setQuantity(int quantity) {
	        Quantity = quantity;
	    }
	    public double getMSP() {
	        return MSP;
	    }
	    public void setMSP(double mSP) {
	        MSP = mSP+(mSP*(50.0/100.0));
	    }
	    public double getSoldPrice() {
	        return soldPrice;
	    }
	    public void setSoldPrice(double soldPrice) {
	        this.soldPrice = soldPrice;
	    }
//	    public double getTotalPrice() {
//	        return totalPrice;
//	    }
//	    public void setTotalPrice(double totalPrice) {
//	        this.totalPrice = totalPrice;
//	    }

}
