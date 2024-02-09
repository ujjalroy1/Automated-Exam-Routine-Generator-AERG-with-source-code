
package aerg;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author User
 */
public class Alldatabase 
{
    
        Connection getConnection() throws SQLException
    {
           
       Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/aerg","root","");
       return conn;
    }
        void insertresult(String date1, String day,String course) throws SQLException
        {
                     Connection conn=getConnection();
                   Statement statement=conn.createStatement();
                   String query="insert into resultroutine values("+"'"+date1+"',"+"'"+day+"',"+"'"+course+"'"+")";
                   statement.executeUpdate(query);
        }
     void insertSpecialDate(String s) throws SQLException
    {
        
         Connection conn=getConnection();
         Statement statement=conn.createStatement();
         String query="insert into specialdate values("+"'"+s+"'"+")";
         statement.executeUpdate(query);
      
    }
          void insertFeedback(String s) throws SQLException
    {
        
         Connection conn=getConnection();
         Statement statement=conn.createStatement();
         String query="insert into feedbackres values("+"'"+s+"'"+")";
         statement.executeUpdate(query);
      
    }
     void insertresultinfo(String le,String se,String ti,String da) throws SQLException
     {
         Connection conn=getConnection();
         Statement statement=conn.createStatement();
           String query="insert into resultinfo values("+"'"+le+"',"+"'"+se+"',"+"'"+ti+"',"+"'"+da+"'"+")";
          statement.executeUpdate(query);
     }
    void deleteAllRecord(String s) throws SQLException
    {
        //DELETE FROM specialdate
                
         Connection conn=getConnection();
         Statement statement=conn.createStatement();
         String query="DELETE FROM "+s;
         statement.executeUpdate(query);        
    }
    List<coursedetails>getcoursedetails(String name) throws SQLException
    {
       // String q="select* from "+name;
        List<coursedetails> stdlist=new ArrayList();
         Connection conn=getConnection();
         Statement statement=conn.createStatement();
        ResultSet rs=statement.executeQuery("select * from "+name);
        while(rs.next())
        {
            coursedetails cds=new coursedetails(rs.getString("coursecode"),rs.getString("credit"));
            stdlist.add(cds);
        }
        return stdlist;
    }
    List<outputstoreclass>getoutputstore() throws SQLException
    {
        List<outputstoreclass> stdlist=new ArrayList();
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        ResultSet rs=statement.executeQuery("select * from "+"resultroutine");
       while(rs.next())
        {
           outputstoreclass cds=new outputstoreclass(rs.getString("dater"),rs.getString("day"),rs.getString("coursecode"));
            stdlist.add(cds);
        }
        return stdlist;
    }
    String[] getholiday(String name,String col) throws SQLException
    {
        String[] hol=new String[1000];
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        ResultSet rs=statement.executeQuery("select * from "+name);
        int i=0;
        while(rs.next())
        {
            hol[i]=rs.getString(col);
            i++;
        }
        return hol;
    }
    String[] getresult1() throws SQLException
    {
        String[] hol=new String[10];
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        ResultSet rs=statement.executeQuery("select * from "+"resultinfo");
              
        while(rs.next())
        {
            hol[0]=rs.getString("level");
            hol[1]=rs.getString("semester");
            hol[2]=rs.getString("time1");
            hol[3]=rs.getString("room");
        }
        
        return hol;
    }
    String getfeedback() throws SQLException
    {
         int cnt=1;
        String s="";
        Connection conn=getConnection();
        Statement statement=conn.createStatement();
        ResultSet rs=statement.executeQuery("select * from "+"feedbackres");
        while(rs.next())
        {
            s=s+cnt;
            s+=". ";
            s+=(rs.getString("store"));
             s+='\n';
             cnt++;
        }
       
        
        
        return s;
    }
    
}
