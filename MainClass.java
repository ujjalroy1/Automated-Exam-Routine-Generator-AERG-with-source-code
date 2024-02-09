
package aerg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
/**
 *
 * @author User
 */
public class MainClass extends Application
{
     public static void main(String[] args) 
    {
         
        launch(args);
        
        
    }

    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent pane=FXMLLoader.load(getClass().getResource("MianFXML.fxml"));
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.show();
        
        
    }
}
