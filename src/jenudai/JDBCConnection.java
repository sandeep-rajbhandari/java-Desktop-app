/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jenudai;

/**
 *
 * @author Sandeep
 */
import java.sql.*;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public  class JDBCConnection {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/jenuDai";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "root";
   public static Connection conn=null;
   
   public  void getConnection() {
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT * FROM jenudai";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("flag");
         int age = rs.getInt("qty");
         String first = rs.getString("customer");
         String last = rs.getString("type");

         //Display values
         System.out.print("ID: " + id);
         System.out.print(", Age: " + age);
         System.out.print(", First: " + first);
         System.out.println(", Last: " + last);
      }
      //STEP 6: Clean-up environment
      rs.close();
      stmt.close();
//      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
//   }finally{
//      //finally block used to close resources
//      try{
//         if(stmt!=null)
//            stmt.close();
//      }catch(SQLException se2){
//      }// nothing we can do
//      try{
//         if(conn!=null)
//            conn.close();
//      }catch(SQLException se){
//         se.printStackTrace();
//      }//end finally try
   }//end try
}//end main
   public static void populate(javax.swing.JComboBox name,String type){
        name.insertItemAt("", 0);
        try{
           Statement stmt = conn.createStatement();
      String sql;
      sql = "SELECT distinct "+type+" FROM jenudai";
      ResultSet rs = stmt.executeQuery(sql);

      //STEP 5: Extract data from result set
      while(rs.next()){
          name.addItem(rs.getString(type));
      }

        }  
        catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
        }
    }
   public static void sumFields(javax.swing.JLabel name,String type){
        try{
           Statement stmt = conn.createStatement();
      String sql;
//      sql = "SELECT SUM(total) FROM jenudai where type='"+type+"' AND flag=1";
            sql = "SELECT SUM(total) FROM jenudai where type='BUY' AND flag=1";

      ResultSet rs = stmt.executeQuery(sql);
      float sum=0;
      

      //STEP 5: Extract data from result set
      while(rs.next()){
          sum=Float.parseFloat(rs.getString(1));
      }
      
      
      name.setText(String.valueOf(sum));

        }  
        catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
        }
    }
   public static void loadData(javax.swing.JTable name,String actualQuery){
        String sql="";
        sql = "select "+actualQuery+" from jenudai group by goods;";

        try{
            Statement  stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            TableModel table=DbUtils.resultSetToTableModel(rs);
            name.setModel(table);
        }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }  
    }

   


}//end FirstExample

