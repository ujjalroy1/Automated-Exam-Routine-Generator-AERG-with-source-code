
package aerg;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreateFXMLController implements Initializable {

      int checkfriday=0;
      int checksaturday=0;
      String specialdatevari="";
          String uselev="";
          String usesem="";
          String useinitialdate="";
          String usetime="";
          String useroom="";
    @FXML
    private AnchorPane createpane;
    @FXML
    private Button specialdatebtn;
    @FXML
    private Button confirmbtn;
    @FXML
    private DatePicker specialdateid;
    @FXML
    private ChoiceBox<String> levelchoosebox;
    @FXML
    private ChoiceBox<String> semesterchoosebox;
    String[] lev={"1","2","3","4"};
    String[] sem={"I","II"};

    @FXML
    private TextField hourid;
    @FXML
    private TextField minutesid;
    @FXML
    private TextField roomid;
    @FXML
    private DatePicker initialdateid;
    @FXML
    private CheckBox checkboxfriday;
    @FXML
    private CheckBox checkboxsatur;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       levelchoosebox.getItems().addAll(lev);
       semesterchoosebox.getItems().setAll(sem);
       levelchoosebox.setOnAction(this::getlevel);
       semesterchoosebox.setOnAction(this::getsemester);
        
    } 
    public void getlevel(ActionEvent event)
    {
        uselev=levelchoosebox.getValue();
        
    }
    public void getsemester(ActionEvent event)
    {
          usesem=semesterchoosebox.getValue();
    }


    @FXML
    private void confirmAction(ActionEvent event) throws IOException, SQLException 
    {
        
            
             Alldatabase db=new Alldatabase();
             db.deleteAllRecord("resultinfo");
             db.deleteAllRecord("resultroutine");
             
             usetime=hourid.getText();
             usetime+='.';
             usetime+=minutesid.getText();
             useroom=roomid.getText();
             
             db.insertresultinfo(uselev, usesem, usetime, useroom);
           //call brutforce approach 
             //routinelogic lg=new routinelogic(useinitialdate,uselev,usesem,usetime,useroom);
             //lg.getoptimizesequence();
             
            //call dp solution
             logicbydp lgdp=new logicbydp(useinitialdate,uselev,usesem,checkfriday,checksaturday);
             
             
             Parent pane = FXMLLoader.load(getClass().getResource("homeFXML.fxml"));
             createpane.getChildren().setAll(pane);
             
           Stage stage = new Stage();
           Parent pane1=FXMLLoader.load(getClass().getResource("notificationFXML.fxml"));
           Scene scene=new Scene(pane1);
           stage.setScene(scene);
           stage.show();
      
        
    }

    @FXML
    private void specialdateActiondate(ActionEvent event) 
    {
        LocalDate ldate=specialdateid.getValue();
        specialdatevari=ldate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
      //  NotifyspecialdateFXMLController.notifyid.setText(specialdatevari);
      

       
    }

    @FXML
    private void specialdateActionbtn(ActionEvent event) throws IOException, SQLException 
    {
            Stage stage = new Stage();
           Parent pane1=FXMLLoader.load(getClass().getResource("notifyspecialdateFXML.fxml"));
           Scene scene=new Scene(pane1);
           stage.setScene(scene);
           stage.show();
           //System.out.println(specialdatevari);
           if(specialdatevari=="")return;
           Alldatabase db=new Alldatabase();
           db.insertSpecialDate(specialdatevari);
             
    }

    @FXML
    private void initialdateAction(ActionEvent event) 
    {
        LocalDate ldate=initialdateid.getValue();
       useinitialdate=ldate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
    }

    @FXML
    private void checkboxfriaction(ActionEvent event) 
    {
        if(checkboxfriday.isSelected())
        {
            checkfriday=1;
        }
    }

    @FXML
    private void checkboxsatAction(ActionEvent event) 
    {
        if(checkboxsatur.isSelected())
        {
            checksaturday=1;
        }
        
    }
    
}
