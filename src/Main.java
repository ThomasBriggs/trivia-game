import java.util.Scanner;
import com.google.gson.Gson;
import Dao.*;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String json = TriviaAPI.get("amount=10", "type=multiple", "category=9");
        APIData data = new Gson().fromJson(json, APIData.class);
        Question[] questions = data.getQuestions();
        Scanner in = new Scanner(System.in);
        int ans;
        int correctAnswers = 0;
        int questionNumber = 1;
        System.out.println(data.getResponseCode());
        System.out.println();
        for (Question question : questions) {
            System.out.println(questionNumber + " - " + question.getCategory() + " - " + question.getDifficulty());
            System.out.println(question.toString(question.getAnswersSorted()));
            System.out.println();
            System.out.print("Please choose an answer (1-4): ");
            ans = in.nextInt();
            if (question.getAnswersSorted()[ans-1].equals(question.getCorrect_answer())){
                System.out.println();
                System.out.println("Correct");
                correctAnswers = correctAnswers + 1;
            }else {
                System.out.println();
                System.out.println("Incorrect");
                System.out.println("Correct answer: " + question.getCorrect_answer());
            }
            System.out.println();
            questionNumber++;
        }
        in.close();
        System.out.println("Correct answers: " + correctAnswers);
    }
}
