import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * QuestionList
 */
public class QuestionList {

    @Expose
    @SerializedName("results")
    private Question[] questions;

    QuestionList(Question[] questions) {
        this.questions = questions;
    }

    /**
     * Returns the question object at the given index
     * 
     * @param index the position of the question to be returned
     * @return the question object
     */
    public Question getQuestion(int index) {
        return this.questions[index];
    }

    public Question[] toArray() {
        return this.questions;
    }

    /**
     * Returns a raw string array of answers given from the TriviaDB
     * 
     * @return a String array of the answers
     */
    public String[] getAnswers(int index) {
        return this.questions[index].getAnswers();
    }

    /**
     * Returns the correct answer for the current question
     * 
     * @return the correct answer
     */
    public String getCorrectAnswer(int index) {
        return this.questions[index].getCorrectAnswer();
    }

    /**
     * Returns how many question objects there are in the question list
     * 
     * @return the amount of questions objects in the list
     */
    public int length() {
        return this.questions.length;
    }

}