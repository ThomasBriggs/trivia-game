import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;

import Dao.*;

/**
 * TriviaGame
 */
public class TriviaGame {

    // TODO Comment this
    public static final int QUESTION_DIFFICULTY_EASY = 0;
    public static final int QUESTION_DIFFICULTY_MEDIUM = 1;
    public static final int QUESTION_DIFFICULTY_HARD = 2;

    public static final int QUESTION_TYPE_MULTIPLE = 0;
    public static final int QUESTION_TYPE_BOOLEAN = 1;

    private final int amount;
    private final int category;
    private final int difficulty;
    private final int type;

    private int questionNumber;

    private Question[] questions;

    private int correct;
    private int incorrect;

    /**
     * Creats a new object of the trivia game which will get questions from the
     * TriviaDB and keep track of your current question as well as the amount of
     * correct and incorrect answers
     * 
     * @param amount     the amount of question in the game
     * @param catagory   the catagory of the game
     * @param difficulty the difficulty of questions in the game, Easy, Medium or
     *                   Hard
     * @param type       the type of questions in the game, Multiple Choice or
     *                   True/False
     * @throws IOException if unable to connect to the TrivaDB
     */
    public TriviaGame(final int amount, final int catagory, final int difficulty, final int type) throws IOException {
        this.questionNumber = 1;
        this.amount = amount;
        this.category = catagory;
        this.difficulty = difficulty;
        this.type = type;
        setQuestions();
    }

    /**
     * Returns the question amount in a format accepted by the API, in the form
     * "amount=x" where x is the amount of questions
     * 
     * @return the formatted amount
     */
    public String getAmount() {
        return "amount=" + this.amount;
    }

    /**
     * Returns the question catagory in a format accepted by the API, in the form
     * "catagory=x" where x is the catagory
     * 
     * @return the formatted catagory
     */
    public String getCatagory() {
        return "catagory=" + this.category;
    }

    /**
     * Returns the question difficulty in a format accepted by the API, in the form
     * "difficulty=x" where x is the difficulty, easy, medium and hard
     * 
     * @return the formatted difficulty
     */
    public String getDifficulty() {
        String difficulty;
        switch (this.difficulty) {
            case 0:
                difficulty = "easy";
                break;
            case 1:
                difficulty = "medium";
                break;
            case 2:
                difficulty = "hard";
                break;
            default:
                throw new IllegalArgumentException();
        }
        return "difficulty=" + difficulty;
    }

    /**
     * Returns the question type in a format accepted by the API, in the form
     * "type=x" where x is the type, multiple and boolean
     * 
     * @return the formatted type
     */
    public String getType() {
        String type;
        switch (this.type) {
            case 0:
                type = "multiple";
                break;
            case 1:
                type = "boolean";
                break;
            default:
                throw new IllegalArgumentException();
        }
        return "type=" + type;
    }

    /**
     * Returns the curernt question number
     * 
     * @return the question number
     */
    public int getQuestionNumber() {
        return this.questionNumber;
    }

    /**
     * Connects to the TriviaDB API and pulls a list of questions and answers, if it
     * cannot connect to the database it will throw an IOException
     * 
     * @throws IOException if uanable to connect to the triviaDB
     */
    private void setQuestions() throws IOException {
        final String json = TriviaAPI.get(getAmount(), getCatagory(), getDifficulty(), getType());
        this.questions = new Gson().fromJson(json, APIData.class).getQuestions();
    }

    /**
     * Returns a array of question objects
     * 
     * @return an array of question objects
     */
    public Question[] getQuestions() {
        return this.questions;
    }

    // TODO Comment this
    public String getQuestion() {
        return this.questions[questionNumber].getQuestion();
    }

    // TODO Comment this
    public String[] getAnswers() {
        return this.questions[questionNumber].getAnswers();
    }

    // TODO Comment this
    public String[] getAnswersSorted() {
        final String[] ansers = getAnswers();
        Arrays.sort(ansers);
        return ansers;
    }

    // TODO Comment this
    public String getCorrectAnswer() {
        return this.questions[questionNumber].getCorrectAnswer();
    }

    // TODO Comment this
    public void increaseQuestionNumber() {
        this.questionNumber++;
    }

    // TODO Comment this
    public void decreaseQuestionNumber() {
        this.questionNumber--;
    }

    // TODO Comment this
    public String answersToString(final String[] answers) {
        String output = "";
        for (int i = 0; i < answers.length; i++) {
            output += (i + 1) + ": " + answers[i] + "\n";
        }
        return output;
    }

    /**
     * Checks if the game has another question
     * 
     * @return {@code true} if another question is avilable, {@code false} otherwise
     */
    public boolean hasNextQuestion() {
        if (this.questionNumber < this.amount) {
            return true;
        } else {
            return false;
        }
    }
}