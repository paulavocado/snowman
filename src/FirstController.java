import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FirstController {

    @FXML
    private Button btnChars;

    @FXML
    private Button btnGames;

    @FXML
    private Button btnMovies;

    @FXML
    private Button btnPhrases;

    @FXML
    private Button btnShows;

    @FXML
    private TextField txtUsername;

    //new stage for controller
    static Stage gameStage = new Stage();

    //declare variables
    File myfile;
    Scanner input;
    static Snowman player = new Snowman();
    ArrayList <String> words = new ArrayList<>();//to input all words for sorting

    @FXML
    void CharactersBtn(ActionEvent event) {
        inputUser();
        myfile = new File("src\\textFiles\\characters.txt");
        getWordFile(myfile);
        nextStage();
    }

    @FXML
    void gamesBtn(ActionEvent event) {
        inputUser();
        myfile = new File("src\\textFiles\\games.txt");
        getWordFile(myfile);
        nextStage();
    }

    @FXML
    void moviesBtn(ActionEvent event) {
        inputUser();
        myfile = new File("src\\textFiles\\movies.txt");
        getWordFile(myfile);
        nextStage();
    }

    @FXML
    void phrasesBtn(ActionEvent event) {
        inputUser();
        myfile = new File("src\\textFiles\\phrases.txt");
        getWordFile(myfile);
        nextStage();
    }

    @FXML
    void tvShowsBtn(ActionEvent event) {
        inputUser();
        myfile = new File("src\\textFiles\\shows.txt");
        getWordFile(myfile);
        nextStage();
    }

    @FXML
    void usernameField(ActionEvent event) {

    }

    public void inputUser(){
        player = new Snowman();//creates player object
        try {
            if(!txtUsername.getText().equals("")){
                player.setName(txtUsername.getText());
            }
            else{//default name for player instead
                player.setName("player-1");
                System.out.println("No username entered. Default name will be 'player 1'");
            }
        } catch (Exception e) {
            System.out.println("Error inputing username " + e.getMessage());
        }
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
                //gets random word from arraylist
                int random = (int)(Math.random() * 20);//random number between 0 and 20
                player.setWord(words.get(random));//the word the user will guess is stored in data field words
                player.wordListAdd(player.getWord());//word added to list. ensures that words are not repeated (for later use)          
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
            App.firstStage.hide();
        } catch (Exception e) {
            System.out.println("Error in load gameplay screen " + e.getMessage());
        }
        
    }
}
