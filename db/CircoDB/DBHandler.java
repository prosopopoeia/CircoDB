/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.CircoDB;

//*
/**
 *
 * @author ComputationalMachine
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author  
 * 
 */
public class DBHandler {
    Customers aCustomer = new Customers();
    Bills aBill = new Bills();
    Properties props = new Properties();
  
  String driver = "org.apache.derby.jdbc.EmbeddedDriver";
  
   // define the Derby connection URL to use 
  String connectionURL = "jdbc:derby://localhost:1527/Circo";
  Connection conn = null;
  Statement s;
  PreparedStatement psInsert;
  ResultSet queryResult = null;  
  
  private void startDB(){
    props.setProperty("user", "name");
    props.setProperty("password", "password");
    try {
        conn = DriverManager.getConnection(connectionURL, props);		 
        System.out.println("Connected to database " + connectionURL);  
        s = conn.createStatement();
    }catch(SQLException e){
        e.printStackTrace();
    }
  }//end startDB
  
  private void closeDB(){
      
        try {
            queryResult.close();
            s.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
      
  }
  public Customers retrieveCustomerName(String last, String first)
   {
       StringBuilder invoices = new StringBuilder();
      
      int id = 0;
      startDB();
      try {
            
            if(first == null || first.equals("")){
                queryResult = s.executeQuery("SELECT * FROM cust, addy WHERE last_name = '" + last + "'");
            }else{
                queryResult = s.executeQuery("SELECT * FROM cust, addy WHERE last_name = '" + last + "' AND first_name = '" + first + "' and cust.id = addy.id" );
            }
            
            while (queryResult.next()){
                aCustomer.setLastName(queryResult.getString("last_Name"));
                aCustomer.setFirstName(queryResult.getString("first_Name"));
                aCustomer.setAddy(queryResult.getString("propertyaddress"));
                aCustomer.setMailAddy(queryResult.getString("mailingaddress"));
                aCustomer.setCity(queryResult.getString("city"));
                aCustomer.setState(queryResult.getString("state"));
                aCustomer.setZip(queryResult.getString("zip"));
                aCustomer.setPhone(queryResult.getString("phone1"));
                aCustomer.setPhone2(queryResult.getString("phone2"));
                id = queryResult.getInt("id");
            }//end while
            
            queryResult = s.executeQuery("SELECT invoice_number FROM bill WHERE id = " + id );
            //ResultSetMetaData rsmd = queryResult.getMetaData();
            while (queryResult.next()){
              invoices.append(queryResult.getString("invoice_number") + ", ");
            }
            aCustomer.setInvoices(invoices.toString());
            
        }  catch (Throwable e)  {   
            System.out.println(" . . . exception thrown:");
            e.printStackTrace(System.out);
         }
      closeDB();
      System.out.println(aCustomer.getLastName());
      System.out.println(aCustomer.getFirstName());
      System.out.println(aCustomer.getCity());
      System.out.println(aCustomer.getPhone());
      System.out.println(aCustomer.getLastName());
      dBShutdown();
      return aCustomer;
   }//end retrieveCustomer
  
  
  
  public Bills retrieveBill(String billIdentifier)
   {
       //StringBuilder invoices = new StringBuilder();
      
      int id = 0;
      startDB();
      try {
            System.out.println("here " + billIdentifier.substring(0, 2));
            if(billIdentifier.substring(0, 3).equals("cls")){
               // System.out.println("here " + billIdentifier.substring(0, 2));
                queryResult = s.executeQuery("SELECT * FROM bill WHERE invoice_number = '" + billIdentifier + "'");
            }else{
                System.out.println("orrrrrrrrrrrr here");
                queryResult = s.executeQuery("SELECT * FROM bill WHERE bill_date = '" + billIdentifier + "'" );
            }
            
            while (queryResult.next()){
                
                System.out.println(queryResult.getString("invoice_number") + "thisssssssssssss");
                aBill.setBillDate(queryResult.getString("bill_date"));
                aBill.setCustID(queryResult.getString("id"));
                aBill.setDateOfService(queryResult.getString("date_of_service"));
                aBill.setLabor(queryResult.getDouble("labor_charge"));
                aBill.setMaterialString(queryResult.getString("material_string"));
                aBill.setMaterialsTotal(queryResult.getDouble("materials"));
                aBill.setMisc(queryResult.getDouble("misc"));
                aBill.setServiceReport(queryResult.getString("service_report"));
                aBill.setTime(queryResult.getString("time"));
                aBill.setTotal(queryResult.getDouble("total"));
                aBill.setTripCharge(queryResult.getDouble("trip_charge"));
            }//end while
            
        }  catch (Throwable e)  {   
            System.out.println(" . . . exception thrown:");
            e.printStackTrace(System.out);
         }
      closeDB();
      dBShutdown();
      return aBill;
   }//end retrieveBill
  //------------------------------------------
  
  
   
  private void dBShutdown(){
            //   ## DATABASE SHUTDOWN SECTION ## 
            /*** In embedded mode, an application should shut down Derby.
               Shutdown throws the XJ015 exception to confirm success. ***/			
            if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
               boolean gotSQLExc = false;
               try {
                  DriverManager.getConnection("jdbc:derby:;shutdown=true");
               } catch (SQLException se)  {	
                  if ( se.getSQLState().equals("XJ015") ) {		
                     gotSQLExc = true;
                  }
               }
               if (!gotSQLExc) {
               	  System.out.println("Database did not shut down normally");
                 
               }  else  {
                  System.out.println("Database shut down normally");	
               }  
            }
        
         
         
      }
  public static void main(String[] args){
      DBHandler dbh = new DBHandler();
      Customers c = new Customers();
      Bills billy = new Bills();
      //c = dbh.retrieveCustomerName("jashton", "zane");
      billy = dbh.retrieveBill("cls-44189886");
      System.out.println(billy.getBillDate());
      System.out.println(billy.getCustID());
      System.out.println(billy.getDateOfService());
      System.out.println(billy.getInvNum());
      System.out.println(billy.getLabor());
      System.out.println(billy.getMaterialString());
      System.out.println(billy.getMisc());
      System.out.println(billy.getSeviceReport());
      System.out.println(billy.getTime());
      
      
      billy = dbh.retrieveBill("12/23/12");
      System.out.println(billy.getBillDate());
      System.out.println(billy.getCustID());
      System.out.println(billy.getDateOfService());
      System.out.println(billy.getInvNum());
      System.out.println(billy.getLabor());
      System.out.println(billy.getMaterialString());
      System.out.println(billy.getMisc());
      System.out.println(billy.getSeviceReport());
      System.out.println(billy.getTime());
  }
}
