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
public class Customers {
    private String firstName, lastName, addy, city, state, zip, phone, mostRecent, secondPhone, mailAddy; 
    private String mailCity, mailState, mailZip, custID;
    public Customers(){
    
    }
    public Customers(String id, String fname, String lname, String addy, String city, String state, String zip,
                    String phone, String phone2){
        custID = id;
        firstName = fname;
        lastName = lname;
        this.addy = addy;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        secondPhone = phone2;
    }
    public Customers(String id, String fname, String lname, String addy, String city, String state, String zip, 
                    String phone, String phone2, String mailAddy, String mailCity, String mailState){
        custID = id;
        firstName = fname;
        lastName = lname;
        this.addy = addy;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        secondPhone = phone2;
        this.mailAddy = mailAddy;
        this.mailCity = mailCity;
        this.mailState = mailState;
    }
    public void setFirstName(String name){
        firstName = name;
    }
    public void setLastName(String name){
        lastName = name;
    }
    public void setAddy(String addy){
        this.addy = addy;
    }    
    public void setCity(String cit){
         city = cit;
    }    
    public void setState(String stat){
        state = stat;
    }   
    public void setZip(String piz){
        zip = piz;
    }
    public void setPhone(String ph){
        phone = ph;
    }
    public void setPhone2(String ph){
        secondPhone = ph;
    }
    public void setMailAddy(String addy){
        mailAddy = addy;
    } 
    public void setMailCity(String addy){
        mailCity = addy;
    }
    public void setMailState(String addy){
        mailState = addy;
    }
    public void setMailZip(String addy){
        mailZip = addy;
    }   
    /** most recent date of work performed for this customer -- tbd**/
    public void setMostRecent(String recent){
        mostRecent = recent;
    }  
    public String getFirstName(){
        return firstName;
    }
    public void setCustID(String id){
        custID = id;
    }  
    public String getCustID(){
        return custID;
    }
    public String getLastName(){
        return lastName;
    }
    public String getAddy(){
        return addy;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public String getZip(){
        return zip;
    }
    public String getPhone(){
        return phone;
    }
    public String getPhone2(){
        return secondPhone;
    }
    public String getMailAddy(){
        return mailAddy;
    }
    public String getMailCity(){
        return mailCity;
    }
    public String getMailState(){
        return mailState;
    }
    public String getMailZip(){
        return mailZip;
    }
}
