import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GamePlayController implements Initializable {

    @FXML
    private Button btnA;

    @FXML
    private Button btnB;

    @FXML
    private Button btnC;

    @FXML
    private Button btnD;

    @FXML
    private Button btnE;

    @FXML
    private Button btnF;

    @FXML
    private Button btnG;

    @FXML
    private Button btnGuess;

    @FXML
    private Button btnH;

    @FXML
    private Button btnHint;

    @FXML
    private Button btnI;

    @FXML
    private Button btnJ;

    @FXML
    private Button btnK;

    @FXML
    private Button btnL;

    @FXML
    private Button btnM;

    @FXML
    private Button btnN;

    @FXML
    private Button btnO;

    @FXML
    private Button btnP;

    @FXML
    private Button btnQ;

    @FXML
    private Button btnR;

    @FXML
    private Button btnS;

    @FXML
    private Button btnT;

    @FXML
    private Button btnU;

    @FXML
    private Button btnV;

    @FXML
    private Button btnW;

    @FXML
    private Button btnX;

    @FXML
    private Button btnY;

    @FXML
    private Button btnZ;

    @FXML
    private Button bttnQuit;

    @FXML
    private ImageView imgSnowman;

    @FXML
    private Label lblAttempt;

    @FXML
    private Label lblHint;

    @FXML
    private Label lblScore;

    @FXML
    private TextArea tareaWord;

    //new stage for final controller
    static Stage finalStage = new Stage();
    //new stage for guess controller
    static Stage guessStage = new Stage();
    //new stage for hint controller
    static Stage hintStage = new Stage();
    //new stage for outcome controller
    static Stage outcomeStage = new Stage();

    //variables used across the program
    String word = FirstController.player.getWord(); 
    static int hint = 1;
    String wordGuessProgress="";
    ArrayList<Integer> positions = new ArrayList<>();//positions of the words selected
    boolean result;

    public void initialize(URL url, ResourceBundle rb){
        //reset hint to 1
        hint = 1;
        System.out.println(word);//to be deleted 

        //cannot use src\\images\\default.jpg
        imgSnowman.setImage(new Image(getClass().getResource("default.jpg").toExternalForm()));

        String lines = ""; //for printing the in text area
        String wordLength = ""; //for getting the spaces in the word (wordguessprogress)
        
        for(int x=0; x < word.length(); x++){
            if(word.charAt(x) == ' '){
                lines += "\n";
                continue;
            }
            lines += "_ ";//gives user the length of the word
            wordLength += " ";
        }
        wordGuessProgress = wordLength + " ";

        //printing the updated values on the controller
        tareaWord.setText(lines);
        lblScore.setText("Score: " + FirstController.player.getScore());
		lblAttempt.setText("Attempts: " + FirstController.player.getAttempt());
        lblHint.setText("Hints: " + hint);
    }

    /* 
     * Find whether or not a letter used is in the word and add the position the letter is in
     * It takes into conderation if the letter is used more than once, and stores all indexes
     * If letter is not found, it will subtract one attempt from the user
     * It will return a boolean to the button method
    */
    public boolean findLetter(char c){
        char temp = ' ';//empty character for when letter (empty = not found)

        //letter added to the letter list. will be used for hints since these need to be taken into consideration
        FirstController.player.letterListAdd(c);

        //finds positions the letter is in (can be more than once)
        for(int x=0; x < word.length(); x++){
            if(word.charAt(x) == ' '){//automatically fills in the blank spaces
                positions.add(x);
            }
            if(word.charAt(x) == c){
                temp = c;
                positions.add(x);//adds the positions where it was found
            }
        }

        //subtracts to attemmps if letter not found at least once
        if(temp == ' '){
            FirstController.player.setAttempt(FirstController.player.getAttempt()-1);//subtracts one attempt
            lblAttempt.setText("Attempt: " + FirstController.player.getAttempt());//prints out the attempt value updated
            return false;//failed to find letter
        }
        return true;//letter was found
    }

    /*
     * Takes into consideration if the letter was found or not (value obtained in that method is sent
     * through the parameter)
     * If the choice was wrong, it will check if they have more attempts left. If yes, then it will switch
     * the image. If not, then it will end the game and send the player to the outcome controller/window
     * If correct, then it will update the output in the textarea so user knows they got a correct letter
     * If the word was guessed correctly, it will send them to the outcome window and update their score
     */
    public void letterSelected(boolean result){

        if(word.length() != wordGuessProgress.length()){
            wordGuessProgress = " ".repeat(word.length());
        }

        //if the wrong letter was selected
        if(!result){
            //wrong and no more attempts left
            if(FirstController.player.getAttempt()==0){
                imgSnowman.setImage(new Image(getClass().getResource("imgSnow6.jpg").toExternalForm()));
                FirstController.player.setOutcome(result);
                outcomeWindow();
            }
            //wrong and more attempts left
            else{
                switchImage();
            }
        }
        else{
            //if correct letter was selected, then change the output in textarea
            for(int x=0; x < positions.size(); x++){
                int position = positions.get(x);
                char [] chars = wordGuessProgress.toCharArray();
                chars[position] = word.charAt(position);
                wordGuessProgress = String.valueOf(chars);
            }
            //prints the wordProgress to the text area
            printTextArea();

            //also compare to see if word guessed finally matches (finish gameplay)
            if(word.equals(wordGuessProgress)){
                //changes the score based on attempts left
                FirstController.player.setScore(FirstController.player.getScore() + FirstController.player.getAttempt());
                //changes the outcome to true
                FirstController.player.setOutcome(true);
                outcomeWindow(); //sends to outcome window
            }
        }
    }

    /*
     * For printing the updated changes in the text area
     * User will refer to this when guessing in the game 
     */
    public void printTextArea(){
        System.out.println(word + word.length());
        System.out.println(wordGuessProgress + wordGuessProgress.length());
        //clears the area so more can be added later
        tareaWord.clear();
        String lines = ""; //stores what will be displayed
        for(int x=0; x < word.length(); x++){
            if(word.charAt(x) == ' '){//new line for a 
                lines += "\n";
            }
            else if(word.charAt(x) == wordGuessProgress.charAt(x)){
                lines += word.charAt(x) + " ";
            }
            else{
                lines += "_ ";
            }
        }
        System.out.println(lines);
        //prints the lines to the text area
        tareaWord.appendText(lines);
    }

    /*
     * Switches the images based on the number of attempts the user has
     * If they guessed a letter incorrectly, it will change it
     */
    public void switchImage(){
        int n = FirstController.player.getAttempt();
        switch(n){
            case 1: imgSnowman.setImage(new Image(getClass().getResource("imgSnow5.jpg").toExternalForm()));
                    break;
            case 2: imgSnowman.setImage(new Image(getClass().getResource("imgSnow4.jpg").toExternalForm()));
                    break;
            case 3: imgSnowman.setImage(new Image(getClass().getResource("imgSnow3.jpg").toExternalForm()));
                    break;
            case 4: imgSnowman.setImage(new Image(getClass().getResource("imgSnow2.jpg").toExternalForm()));
                    break;
            case 5: imgSnowman.setImage(new Image(getClass().getResource("imgSnow1.jpg").toExternalForm()));
                    break;
            case 6: imgSnowman.setImage(new Image(getClass().getResource("imgSnow0.jpg").toExternalForm()));
                    break;
        }
    }

    @FXML
    void quitBtn(ActionEvent event) {
        //confirms with the user if they want to quit the gameplay first
        Alert quit = new Alert(AlertType.CONFIRMATION);
        quit.setTitle("Exit Window");
        quit.setContentText("Would you like to exit the game?");
        quit.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK){
                finalWindow();//displays the final window/controller
            }
        });
    }
    
    @FXML
    void guessBtn(ActionEvent event) {
        if(FirstController.player.getAttempt() >=3){ //user needs 3 attemps or more
            //guessStage
            try {
                //changes it in case they get it wrong... only way i could do it
                FirstController.player.setAttempt(FirstController.player.getAttempt()-2);
                switchImage();
                lblAttempt.setText("Attempts: " + FirstController.player.getAttempt());
                btnGuess.setDisable(true); //guess button will be disabled
                //goes to the next stage
                FXMLLoader loader = new FXMLLoader(getClass().getResource("guess.fxml"));
                Parent root = loader.load();
                guessStage.setScene(new Scene(root));
                guessStage.setTitle("Guess Word");
                guessStage.show();
                FirstController.gameStage.hide();
            } catch (Exception e) {
                System.out.println("Error to load hint screen " + e.getMessage());
            }
        }
        else{
            //if not enough attempts, an error message will be displayed
            Alert a = new Alert(AlertType.ERROR);//new window
            a.setTitle("Error Message");
            a.setContentText("Not enough attempts (3 needed)");
            a.show();
            btnGuess.setDisable(true); //guess button will be disabled
        }
    }

    @FXML
    void hintBtn(ActionEvent event) {
        if(FirstController.player.getAttempt() >=2 && hint >= 1){ //user needs 2 attemps or more
            Alert h = new Alert(AlertType.CONFIRMATION);
            h.setTitle("Hint Window");
            h.setContentText("Would you like a hint? 1 point will be subtracted");
            h.showAndWait().ifPresent(response -> {
                if(response == ButtonType.OK){
                    //subtracts one hint if they select okay
                    hint = 0;
                    lblHint.setText("Hints: " + hint);
                    //subtracts one attempt if they select okay
                    FirstController.player.setAttempt(FirstController.player.getAttempt()-1);
                    switchImage();
                    lblAttempt.setText("Attempts: " + FirstController.player.getAttempt());
                    //disables hint button
                    btnHint.setDisable(true);
                    hintWindow();
                }
            });
        }
        else{
            //if not enough attempts, an error message will be displayed
            Alert a = new Alert(AlertType.ERROR);
            a.setTitle("Error Message");
            a.setContentText("Not enough attempts (2 needed)");
            a.show();
            btnHint.setDisable(true); //hint button disabled
        }
    }

    /*
     * Goes to the final window (the stage)
     */
    public void finalWindow(){
        //finalStage
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Final.fxml"));
            Parent root = loader.load();
            finalStage.setScene(new Scene(root));
            finalStage.setTitle("Thanks for Playing");
            finalStage.show();
            FirstController.gameStage.close();
        } catch (Exception e) {
            System.out.println("Error to load final screen " + e.getMessage());
        }
    }

    /*
     * Goes to the hint window (the stage)
     */
    public void hintWindow(){
        //hintStage
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hint.fxml"));
            Parent root = loader.load();
            hintStage.setScene(new Scene(root));
            hintStage.setTitle("Hint");
            hintStage.show();
            FirstController.gameStage.close();
        } catch (Exception e) {
            System.out.println("Error to load hint screen " + e.getMessage());
        }
    }


    /*
     * Checks whether this is the first controller stage or the category stage
     
    public void checkStage(){
        if(FirstController.gameStage != null && FirstController.gameStage.isShowing()){
            FirstController.gameStage.close();
        }
        if(CategoryController.gameStage != null  && CategoryController.gameStage.isShowing()){
            CategoryController.gameStage.close();
        }
    }
    */

    /*
     * Goes to the outcome window (the stage)
     */
    public void outcomeWindow(){
        //outcomeStage
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("outcome.fxml"));
            Parent root = loader.load();
            outcomeStage.setScene(new Scene(root));
            outcomeStage.setTitle("Outcome");
            outcomeStage.show();
            FirstController.gameStage.hide();
        } catch (Exception e) {
            System.out.println("Error to load outcome screen " + e.getMessage());
        }
    }

    @FXML
    void a(ActionEvent event) {
        result = findLetter('a');//sends a character and checks if it is present in the word
        btnA.setDisable(true);//disables button
        letterSelected(result);//depending on the result, it changes values
    }

    @FXML
    void b(ActionEvent event) {
        result = findLetter('b');
        btnB.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void c(ActionEvent event) {
        result = findLetter('c');
        btnC.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void d(ActionEvent event) {
        result = findLetter('d');
        btnD.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void e(ActionEvent event) {
        result = findLetter('e');
        btnE.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void f(ActionEvent event) {
        result = findLetter('f');
        btnF.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void g(ActionEvent event) {
        result = findLetter('g');
        btnG.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void h(ActionEvent event) {
        result = findLetter('h');
        btnH.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void i(ActionEvent event) {
        result = findLetter('i');
        btnI.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void j(ActionEvent event) {
        result = findLetter('j');
        btnJ.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void k(ActionEvent event) {
        result = findLetter('k');
        btnK.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void l(ActionEvent event) {
        result = findLetter('l');
        btnL.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void m(ActionEvent event) {
        result = findLetter('m');
        btnM.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void n(ActionEvent event) {
        result = findLetter('n');
        btnN.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void o(ActionEvent event) {
        result = findLetter('o');
        btnO.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void p(ActionEvent event) {
        result = findLetter('p');
        btnP.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void q(ActionEvent event) {
        result = findLetter('q');
        btnQ.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void r(ActionEvent event) {
        result = findLetter('r');
        btnR.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void s(ActionEvent event) {
        result = findLetter('s');
        btnS.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void t(ActionEvent event) {
        result = findLetter('t');
        btnT.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void u(ActionEvent event) {
        result = findLetter('u');
        btnU.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void v(ActionEvent event) {
        result = findLetter('v');
        btnV.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void w(ActionEvent event) {
        result = findLetter('w');
        btnW.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void x(ActionEvent event) {
        result = findLetter('x');
        btnX.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void y(ActionEvent event) {
        result = findLetter('y');
        btnY.setDisable(true);//disables button
        letterSelected(result);
    }

    @FXML
    void z(ActionEvent event) {
        result = findLetter('z');
        btnZ.setDisable(true);//disables button
        letterSelected(result);
    }

}