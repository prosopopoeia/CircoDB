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
import java.util.Properties;

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
  public Customers retrieveCustomer(Customers cust, Bills bill)
   {
      props.setProperty("user", "name");
      props.setProperty("password", "password");
      String id = null;
      try {
            // Create and connect to the database.
            conn = DriverManager.getConnection(connectionURL,props);		 
            System.out.println("Connected to database " + connectionURL);  
            s = conn.createStatement();
            queryResult = s.executeQuery("select * from cust, addy");
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
                id = queryResult.getString("id");
            }//end while
            queryResult = s.executeQuery("SELECT invoice_number FROM bill WHERE id = '" + id + "'");
            while (queryResult.next()){
                
            }
            queryResult.close();
            s.close();
            conn.close();
        }  catch (Throwable e)  {   
            System.out.println(" . . . exception thrown:");
            e.printStackTrace(System.out);
         }
            
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
                  //conn.close(); // try again
               }  else  {
                  System.out.println("Database shut down normally");	
               }  
            }
            
         //  Beginning of the primary catch block: prints stack trace
         
         return aCustomer;
      }
}
