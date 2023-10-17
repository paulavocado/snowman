import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OutcomeController implements Initializable {

    @FXML
    private Button btnEnd;

    @FXML
    private Button btnTry;

    @FXML
    private Label lblOutcome;

    @FXML
    private Label lblWord;

    //new stage for final controller
    static Stage finalStage = new Stage();
    //new stage for the categories controller
    static Stage catStage = new Stage();

    public void initialize(URL url, ResourceBundle rb){
        //****NEED THE LABEL FOR THE OUTCOME!!! */
        if(FirstController.player.getOutcome()){
            //if word was guessed correctly, then add to the appropiate list
            FirstController.player.wordsCorrectAdd(FirstController.player.getWord());
        }
        else{
            //if word was guessed incorrectly, the add to the appropiate list
            FirstController.player.wordsIncorrectAdd(FirstController.player.getWord());
        }
        //message letting the user know the complete word
        lblOutcome.setText("You Guessed " + (FirstController.player.getOutcome()? "Correctly" : "Incorrectly"));
        lblWord.setText("The word was " + FirstController.player.getWord());
    }

    @FXML
    void doneBtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Final.fxml"));
            Parent root = loader.load();
            finalStage.setScene(new Scene(root));
            finalStage.setTitle("Thanks for Playing");
            finalStage.show();
            GamePlayController.outcomeStage.close();
            

            /* 
            if(GamePlayController.outcomeStage != null){
                GamePlayController.outcomeStage.close();
            }
            if(GuessController.outcomeStage != null){
                GuessController.outcomeStage.close();
            }
            */

        } catch (Exception e) {
            System.out.println("Error to load final screen " + e.getMessage());
        }
    }

    @FXML
    void tryAgainBTn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("category.fxml"));
            Parent root = loader.load();
            catStage.setScene(new Scene(root));
            catStage.setTitle("Select a Category");
            catStage.show();
            GamePlayController.outcomeStage.close();
        } catch (Exception e) {
            System.out.println("Error to load final screen " + e.getMessage());
        }
    }

}
