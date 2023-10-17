import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class hintController implements Initializable {

    @FXML
    private Button btnCont;

    @FXML
    private Label lblMessage;

    public void initialize(URL url, ResourceBundle rb){
        boolean found;
        do{
            found = true;

            //generates random letter
            int num = (int)(Math.random() * FirstController.player.getWord().length());
            char c = FirstController.player.getWord().charAt(num);
            
            //when letter is used, it will be false and 
            if (FirstController.player.getLettersUsed().contains(c)) {
                found = false;
            }
            
            if(found){
                //displays the message
                lblMessage.setText(FirstController.player.getWord().charAt(num) + " is one of the letters!");
                //this i will have to see if i want to add... ***********
                //FirstController.player.letterListAdd(FirstController.player.getWord().charAt(num));
            }
        }while(!found);//loop will run again if letter has been used
    }

    @FXML
    void btnContinue(ActionEvent event) {
        //goes back to the gameplay
        if(FirstController.player.getWords().size() == 1){
            FirstController.gameStage.show();
        }
        else{
            CategoryController.gameStage.show();
        }
        //FirstController.gameStage.show();
        GamePlayController.hintStage.close();

        /* 
        //goes back to gameplay controller
        if(FirstController.gameStage != null && FirstController.player.getWords().size() > 1){
            FirstController.gameStage.show();
            GamePlayController.hintStage.close();
        }
        if(CategoryController.gameStage != null){
            CategoryController.gameStage.show();
            GamePlayController.hintStage.close();
        }
        */
    }

}
