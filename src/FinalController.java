import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.fxml.Initializable;

public class FinalController implements Initializable {

    @FXML
    private Label lblName;

    @FXML
    private TextArea tareaInfo;


    public void initialize(URL url, ResourceBundle rb){
        lblName.setText(FirstController.player.getName());
        tareaInfo.appendText("Score: " + FirstController.player.getScore());

        String wordsCorrect = "";
        for(int x=0; x<FirstController.player.getWordsCorrect().size(); x++){
            wordsCorrect += FirstController.player.getWordsCorrect().get(x) + ", ";
        }
        tareaInfo.appendText("\nWords Guessed: " + wordsCorrect);

        String wordsIncorrect = "";
        for(int x=0; x<FirstController.player.getWordsIncorrect().size(); x++){
            wordsIncorrect += FirstController.player.getWordsIncorrect().get(x) + ", ";
        }
        tareaInfo.appendText("\nWords missed: " + wordsIncorrect);
    }
}
