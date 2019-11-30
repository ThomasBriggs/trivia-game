import com.google.gson.Gson;

/**
 * TriviaGame
 */
public class TriviaGame {

    private String amount;
    private String category;
    private String difficult;
    private String type;

    private int questionNumber;

    private Question[] questions;

    public TriviaGame() throws Exception {
        getQuestions();
    }

    public void getQuestions() throws Exception {
        String json = Trivia.get("amount=5", "category=9", "difficulty=easy", "type=multiple");
        setQuestions(new Gson().fromJson(json, APIData.class).getQuestions());
    }

    public void setQuestions(Question[] quesitons){
        this.questions = quesitons;
    }

    public Question getQuestion(int questionNumber){
        return  questions[questionNumber];
    }

    // public Question isAnswerCorrect(){

    // }
    
}