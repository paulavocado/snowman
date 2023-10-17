import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GuessController {

    @FXML
    private Button btSubmit;

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtGuess;

    //new stage for outcome controller
    static Stage outcomeStage = new Stage();

    public void outcomeScreen(){
        //outcomeStage
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("outcome.fxml"));
            Parent root = loader.load();
            outcomeStage.setScene(new Scene(root));
            outcomeStage.setTitle("Outcome");
            outcomeStage.show();
            FirstController.gameStage.close();
        } catch (Exception e) {
            System.out.println("Error to load outcome screen " + e.getMessage());
        }
    }

    @FXML
    void SubmitBtn(ActionEvent event) {
        if(txtGuess.getText().equalsIgnoreCase(FirstController.player.getWord())){
            //attempts will reset (refer to the gameplay controller comment)
            FirstController.player.setAttempt(FirstController.player.getAttempt()+2);
            //outcome in player object becomes true
            FirstController.player.setOutcome(true);;
            //adds remaining attempts to the score (2 extra points!!)
            FirstController.player.setScore(FirstController.player.getScore() + FirstController.player.getAttempt() + 2);

            //FirstController.gameStage.show();
            //GamePlayController.guessStage.close();

            
            if(FirstController.player.getWords().size() >= 1){
                outcomeScreen();
            }
            //FirstController.gameStage.show();
            GamePlayController.guessStage.close();

            /*
            //goes back to gameplay controller
            if(FirstController.gameStage != null && FirstController.gameStage.isShowing()){
                outcomeScreen();//goes to outcome screen
                GamePlayController.guessStage.close();
            }
            if(CategoryController.gameStage != null && CategoryController.gameStage.isShowing()){
                outcomeScreen();//goes to outcome screen
                GamePlayController.guessStage.close();
            }
             */
            
        }
        else{
            Alert wrong = new Alert(AlertType.CONFIRMATION);
            wrong.setTitle("Wrong guess");
            wrong.setContentText("Your guess was wrong");
            wrong.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK){
                    //goes back to the gameplay
                    if(FirstController.player.getWords().size() == 1){
                        FirstController.gameStage.show();
                    }
                    else{
                        CategoryController.gameStage.show();
                    }
                    //FirstController.gameStage.show();
                    GamePlayController.guessStage.close();
                }
            });
        }
    }

    //not needed!!!!
    @FXML
    void TextField(ActionEvent event) {

    }

    @FXML
    void goBackBtn(ActionEvent event) {
        /* 
        //goes back to gameplay controller
        if(FirstController.gameStage != null && FirstController.gameStage.isShowing()){
            FirstController.gameStage.show();
            GamePlayController.guessStage.close();
        }
        if(CategoryController.gameStage != null && CategoryController.gameStage.isShowing()){
            CategoryController.gameStage.show();
            GamePlayController.guessStage.close();
        }
        */

        if(FirstController.player.getWords().size() == 1){
            FirstController.gameStage.show();
        }
        
        //FirstController.gameStage.show();
        GamePlayController.guessStage.close();

        //FirstController.gameStage.show();
        //GamePlayController.guessStage.close();
        
    }

}
