import java.util.ArrayList;

public class Snowman {
    
    private String name;
    private int attempt;
    private int score;
    private String word;
    private boolean outcome;
    
    //temporarily stores the words from the file
    private ArrayList <String> words = new ArrayList<>();
    private ArrayList <String> wordsCorrect = new ArrayList<>();
    private ArrayList <String> wordsIncorrect = new ArrayList<>();
    private ArrayList<Character> lettersUsed = new ArrayList<>();

    public Snowman(){ //default values
        this.attempt = 7;
        this.score = 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAttempt() {
        return attempt;
    }
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public ArrayList<String> getWords() {
        return words;
    }
    public String getWord(){
        return word;
    }
    public void setWord(String word){
        this.word = word;
    }
    public void setOutcome(boolean outcome){
        this.outcome = outcome;
    }
    public boolean getOutcome(){
        return this.outcome;
    }
    public ArrayList<Character> getLettersUsed() {
        return lettersUsed;
    }
    public ArrayList<String> getWordsCorrect() {
        return wordsCorrect;
    }
    public ArrayList<String> getWordsIncorrect() {
        return wordsIncorrect;
    }
    
    //to add words to the list
    public void wordListAdd(String word){
        words.add(word);
    }
    //to add letters to the list
    public void letterListAdd(char c){
        lettersUsed.add(c);
    }
    //to add letters to the list
    public void wordsCorrectAdd(String w){
        wordsCorrect.add(w);
    }
    //to add letters to the list
    public void wordsIncorrectAdd(String w){
        wordsIncorrect.add(w);
    }
}
