import java.util.Arrays;

import com.google.gson.Gson;

/**
 * TriviaGame
 */
public class TriviaGame {

    private String amount = "5";
    private String category;
    private String difficult;
    private String type;

    private int questionNumber;

    private Question[] questions;

    public TriviaGame() throws Exception {
        getQuestionsAPI();
        questionNumber = 1;
    }

    public int getAmount(){
        return Integer.parseInt(this.amount);
    }

    public int getQuestionNumber(){
        return this.questionNumber;
    }

    public void getQuestionsAPI() throws Exception {
        String json = Trivia.get("amount=5", "category=9", "difficulty=easy", "type=multiple");
        setQuestions(new Gson().fromJson(json, APIData.class).getQuestions());
    }

    public void setQuestions(Question[] quesitons){
        this.questions = quesitons;
    }

    public Question[] getQuestions(){
        return this.questions;
    }

    public String getQuestion(){
        return  this.questions[questionNumber].getQuestion();
    }

    public String[] getAnswers(){
        return this.questions[questionNumber].getAnswers();
    }

    public String[] getAnswersSorted(){
        String[] ansers = getAnswers();
        Arrays.sort(ansers);
        return ansers;
    }

    public String getCorrectAnswer(){
        return this.questions[questionNumber].getCorrect_answer();
    }

    public void increaseQuestionNumber(){
        this.questionNumber++;
    }

    public void decreaseQuestionNumber(){
        this.questionNumber--;
    }

    public String answersToString(String[] answers){
        String output = "";
        for (int i = 0; i < answers.length; i++) {
            output += (i+1) + ": " + answers[i] + "\n";
        }
        return output;
    }
}