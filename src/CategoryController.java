import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CategoryController {

    @FXML
    private Button btnCharacters;

    @FXML
    private Button btnGames;

    @FXML
    private Button btnMovies;

    @FXML
    private Button btnPhrase;

    @FXML
    private Button btnTv;

    //new stage for controller
    static Stage gameStage = new Stage();

    //declare variables
    File myfile;
    Scanner input;
    ArrayList <String> words = new ArrayList<>();//to input all words for sorting

    @FXML
    void CharactersBtn(ActionEvent event) {
        myfile = new File("src\\textFiles\\characters.txt");
        resetValues();
        getWordFile(myfile);
        nextStage();
    }

    @FXML
    void GamesBtn(ActionEvent event) {
        myfile = new File("src\\textFiles\\games.txt");
        resetValues();
        getWordFile(myfile);
        nextStage();
    }

    @FXML
    void MoviesBtn(ActionEvent event) {
        myfile = new File("src\\textFiles\\movies.txt");
        resetValues();
        getWordFile(myfile);
        nextStage();
    }

    @FXML
    void PhrasesBtn(ActionEvent event) {
        myfile = new File("src\\textFiles\\phrases.txt");
        resetValues();
        getWordFile(myfile);
        nextStage();
    }

    @FXML
    void tvShowsBtn(ActionEvent event) {
        myfile = new File("src\\textFiles\\shows.txt");
        resetValues();
        getWordFile(myfile);
        nextStage();
    }

    public void resetValues(){
        FirstController.player.getLettersUsed().clear();//clears the letters used by user
        FirstController.player.setAttempt(7);//sets attempts to 7 ibce again
    }


    public void getWordFile(File myfile){
        try {
            //checks if file exists
            if(myfile.exists()){
                input = new Scanner(myfile);
                while(input.hasNext()){//gets each word on a new line
                    String word = input.nextLine();
                    Scanner scr = new Scanner(word);
                    scr.useDelimiter("\\|");//uses special delimeter |
                    words.add(scr.next());//adds the word to array list "words"
                }
                int x;
                do {
                    x = 1; 
                    //gets random word from arraylist
                    int random = (int)(Math.random() * 20);//random number between 0 and 20
                    if(FirstController.player.getWords().contains(words.get(random))){
                        x=0;
                    }
                    else{
                        FirstController.player.setWord(words.get(random));//the word the user will guess is stored in data field words
                        FirstController.player.wordListAdd(FirstController.player.getWord());//word added to list. ensures that words are not repeated (for later use)  
                    }
                }while(x==0);

                input.close();//close scanner
            }

            else{
                System.out.println("File does not exist");
            }

            /*
            * once complete, delete arraylist in case user wants to play again (add other words)
            * from other categories
            */
            words.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void nextStage(){  
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gamePlay.fxml"));
            Parent root = loader.load();
            gameStage.setScene(new Scene(root));
            gameStage.setTitle("Gameplay");
            gameStage.show();
            OutcomeController.catStage.close();
        } catch (Exception e) {
            System.out.println("Error in load gameplay screen " + e.getMessage());
        } 
    }
}
