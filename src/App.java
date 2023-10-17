/**
 * Programmer : simranjeet kaur<br>
 * Program : assignment 3 for java
 * Date : March 23, 2023
*/

import java.io.IOException;
import java.io.InvalidObjectException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

    static Stage firstStage = new Stage();

    @Override
    /**
     * creation of stage and displaying it
     */
    public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("first.fxml"));
    Scene scene = new Scene(root);      
    firstStage.setScene(scene);
    firstStage.setTitle("Snowman Star");
    firstStage.show();    
}
    //main method
    public static void main(String[] args) {
        launch(args);
    }
}