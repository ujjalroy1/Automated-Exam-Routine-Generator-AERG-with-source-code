/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FeedbackFXMLController implements Initializable {

    @FXML
    private TextField feedbackfield;
    @FXML
    private Button feedbacksendbtn;
    @FXML
    private TextArea feedbackarea;
   String comment="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Alldatabase db=new Alldatabase();
       
        try {
            comment=db.getfeedback();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         feedbackarea.setText(comment);
    }    

    @FXML
    private void feedbacksendAction(ActionEvent event) throws SQLException, IOException
    {
         String ans;
         ans=feedbackfield.getText();
         Alldatabase db=new Alldatabase();
        db.insertFeedback(ans);
         comment=db.getfeedback();
         
         feedbackarea.setText(comment);
         feedbackfield.clear();
        
           Stage stage = new Stage();
           Parent pane1=FXMLLoader.load(getClass().getResource("feedbacksendnotificationFXML.fxml"));
           Scene scene=new Scene(pane1);
           stage.setScene(scene);
           stage.show();
    }

    @FXML
    private void feedbackfieldAction(ActionEvent event) {
    }
    
}
