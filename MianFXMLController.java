
package aerg;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
public class MianFXMLController implements Initializable {

    @FXML
    private AnchorPane outputpane;
    @FXML
    private AnchorPane messagepane;
    @FXML
    private AnchorPane optionpane;
    @FXML
    private Button homebtn;
    @FXML
    private Button createbtn;
    @FXML
    private Button showbtn;
    @FXML
    private Button contactbtn;
    @FXML
    private Button feedbackbtn;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
                    Parent pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("homeFXML.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MianFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    outputpane.getChildren().setAll(pane);
        
        
    }    

    @FXML
    private void homeAction(ActionEvent event) throws IOException 
    {
                     Parent pane = FXMLLoader.load(getClass().getResource("homeFXML.fxml"));
                    outputpane.getChildren().setAll(pane);
    }

    @FXML
    private void createAction(ActionEvent event) throws IOException, SQLException 
    {
            Alldatabase db=new Alldatabase();
            db.deleteAllRecord("specialdate");
             Parent pane = FXMLLoader.load(getClass().getResource("CreateFXML.fxml"));
              outputpane.getChildren().setAll(pane);
        
    }

    @FXML
    private void showAction(ActionEvent event) throws IOException
    {
                 //   Alldatabase db=new Alldatabase();
            //db.deleteAllRecord("specialdate");
             Parent pane = FXMLLoader.load(getClass().getResource("showFXML.fxml"));
              outputpane.getChildren().setAll(pane);
        
    }

    @FXML
    private void contactAction(ActionEvent event) throws IOException
    {
           Parent pane = FXMLLoader.load(getClass().getResource("contactFXML.fxml"));
            outputpane.getChildren().setAll(pane);
    }

    @FXML
    private void feedbackAction(ActionEvent event) throws IOException
    {
              Parent pane = FXMLLoader.load(getClass().getResource("feedbackFXML.fxml"));
              outputpane.getChildren().setAll(pane);
        
    }
    
}
