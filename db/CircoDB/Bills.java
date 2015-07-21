/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.CircoDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author ComputationalMachine
 */
public class Bills {
    
    private String custID, dateOfService, invNum, billDate, serviceReport, materialString, time;
    private double total, labor, materialsTotal, tripCharge, misc; 
    private boolean tripFee; 
    
    
    public void calcTotal(){
        total = labor + materialsTotal + tripCharge + misc;
    }
    public void setCustID(String id){
        custID = id;
    }
    public void setDateOfService(String id){
        dateOfService = id;
    }
    public void setInvNum(String id){
        invNum = id;
    }
    public void setBillDate(String id){
        billDate = id;
    }
    public void setServiceReport(String value){
        serviceReport = value;
    }
    public void setMaterialString(String value){
        materialString = value;
    }
    public void setTotal(double value){
        total = value;
    }
    public double getTotal(){
        return total;
    }
    public void setTime(String value){
        time = value;
    }
    public String getTime(){
        return time;
    }
    public void setLabor(double value){
        labor = value;
    }
    public double getLabor(){
        return labor;
    }
    public void setMaterialsTotal(double value){
        materialsTotal = value;
    }
    public double getTripCharge(){
        return tripCharge;
    }
    public void setTripCharge(double value){
        tripCharge = value;
    }
    public double getMaterialsTotal(){
        return materialsTotal;
    }
    
    public void setMisc(double value){
        misc = value;
    }
    public double getMisc(){
        return misc;
    }
    public String getCustID(){
        return custID;
    }
    public String getSeviceReport(){
        return serviceReport;
    }
    public String getDateOfService(){
        return dateOfService;
    }
    public String getInvNum(){
        return invNum;
    }
    public String getBillDate(){
        return billDate;
    }
    public String getMaterialString(){
        return materialString;
    }
    
}

