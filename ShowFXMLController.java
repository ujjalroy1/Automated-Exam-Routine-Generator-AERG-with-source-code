/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package aerg;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ShowFXMLController implements Initializable {

    @FXML
    private AnchorPane ouputpane;
    @FXML
    private TextArea outputarea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try {
            // TODO
            dekhao();
        } catch (SQLException ex) {
            Logger.getLogger(ShowFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    void dekhao() throws SQLException
    {
        
            String ans="";
            Alldatabase db=new Alldatabase();
           String[] ok=db.getresult1();
           ans+="Faculty : ";
           ans+="CSE\n";
           ans+="Name of the examination : B.Sc(engineering) in CSE Level "+ok[0]+" Semester "+ok[1]+"\n";
           ans+="Center : Dr.Muhammad Qudrat-i-Khuda academic building, HSTU\n";
           ans+="Time : "+ok[2]+" AM\n";
           ans+="Room : "+ok[3]+"\n\n";
           ans+="DATE                             DAY                    COURSE\n";
           Alldatabase db1=new Alldatabase();
           List<outputstoreclass> anslist=db1.getoutputstore();
           for(outputstoreclass u:anslist)
           {
               ans+=u.date1;
               ans+="               ";
               int r=u.day.length();
               String x;
               x=u.day;
               ans+=x;
               int l=(20-r);
              // System.out.println(x+" "+(30-r));
               for(int i=0;i<l;i++)
               {
                   ans+=" ";
               }
               
               if(x.equalsIgnoreCase("SUNDAY"))
               {
                   ans+="    ";
                   //System.out.println("hi");
               }
               if(x.equalsIgnoreCase("TUESDAY"))
               {
                   ans+="    ";
                   //System.out.println("hi");
               }
               if(x.equalsIgnoreCase("MONDAY"))
               {
                   ans+="   ";
                   //System.out.println("hi");
               }
               
             if(x.equalsIgnoreCase("THURSDAY"))
               {
                   ans+="  ";
                   //System.out.println("hi");
               }
             if(x.equalsIgnoreCase("SATURDAY"))
               {
                   ans+="  ";
                   //System.out.println("hi");
                 
               }
              if(x.equalsIgnoreCase("FRIDAY"))
               {
                   ans+="      ";
                   //System.out.println("hi");
                   
               }
             
              // System.out.println(x+" "+(30-r));
               
               ans+=u.course;
               ans+='\n';
           }
           
           outputarea.setText(ans);
           
    }

}
