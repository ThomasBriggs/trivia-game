import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.text.StringEscapeUtils;

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

    @Expose
    @SerializedName("results")
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
        this.questionNumber = 0;
        this.amount = amount;
        this.category = catagory;
        this.difficulty = difficulty;
        this.type = type;
        this.correct = 0;
        this.incorrect = 0;
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
        return "category=" + this.category;
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
     * Returns the current question index of the question in the array plus 1 to
     * account for arrays start at 0
     * 
     * @return the question number
     */
    public int getQuestionNumber() {
        return this.questionNumber + 1;
    }

    /**
     * Connects to the TriviaDB API and pulls a list of questions and answers, if it
     * cannot connect to the database it will throw an IOException
     * 
     * @throws IOException if uanable to connect to the triviaDB
     */
    private void setQuestions() throws IOException {
        final String json = TriviaAPI.get(getAmount(), getCatagory(), getDifficulty(), getType());
        this.questions = new Gson().fromJson(json, TriviaGame.class).getQuestions();
    }

    /**
     * Returns a array of question objects
     * 
     * @return an array of question objects
     */
    public Question[] getQuestions() {
        return this.questions;
    }

    /**
     * Returns the question object for the current question
     * 
     * @return the question object
     */
    public Question getQuestion() {
        return this.questions[questionNumber];
    }

    /**
     * Returns a raw string array of answers given from the TriviaDB
     * 
     * @return a String array of the answers
     */
    public String[] getAnswersRaw() {
        return this.questions[questionNumber].getAnswers();
    }

    private String[] getAnswersSorted() {
        final String[] answers = getAnswersRaw();
        Arrays.sort(answers);
        return answers;
    }

    /**
     * Returns a string of the answers formatted and given in alphabetical order
     * 
     * @return a String of answers
     */
    public String printAnswers() {
        return escape(formatAnswers(getAnswersSorted()));
    }

    /**
     * Takes an array of answers and returns a formated String of the answers with
     * the htmlcharacters removed
     * 
     * @param answers the string array of answers
     * @return formated string of answers
     */
    public static String formatAnswers(String[] answers) {
        String ans1 = answers[0];
        String ans2 = answers[1];
        String ans3 = answers[2];
        String ans4 = answers[3];
        return String.format("1: %s\n2: %s\n3: %s\n4: %s", ans1, ans2, ans3, ans4);
    }

    /**
     * Returns the correct answer for the current question
     * 
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return this.questions[questionNumber].getCorrectAnswer();
    }

    /**
     * Moves the game onto the next question, any information returned from the
     * object will be about the next nextion in the list after this has been run
     */
    public void next() {
        this.questionNumber++;
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

    /**
     * Creates a question title consisting of the current question number, the
     * catagory of the question and the difficulty in the format Number - Catagory -
     * Difficulty
     * 
     * @return
     */
    public String getQuestionTitle() {
        return getQuestionNumber() + " - " + getQuestion().getQuestionTitle();
    }

    /**
     * Checks if the answer given as a String matches the correct answer of the
     * question
     * 
     * @param answer the correct answer to check
     * @return {@code true} if the answer is correct, {@code false} otherwise
     */
    public boolean checkAnswer(String answer) {
        if (answer.equals(getCorrectAnswer())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the answer is correct given the answer number when displayed using
     * the printAnswers() function
     * 
     * @param answer the number of the answer
     * @return {@code true} if the answer is correct, {@code false} otherwise
     */
    public boolean checkAnswer(int answer) {
        return checkAnswer(getAnswersSorted()[answer - 1]);
    }

    /**
     * Incerase the correct answer score by 1
     */
    public void correct() {
        this.correct++;
    }

    /**
     * Increases the incorrect answer score by 1
     */
    public void incorrect() {
        this.incorrect++;
    }

    /**
     * Returns an array of the current scores, wih the correct answers in index 0
     * and incorrect answers at index 1
     * 
     * @return an array of current scores
     */
    public int[] getScore() {
        return new int[] { this.correct, this.incorrect };
    }

    public static String escape(String string) {
        return StringEscapeUtils.unescapeHtml4(string);
    }
}