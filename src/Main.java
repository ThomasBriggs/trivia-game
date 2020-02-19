import java.util.Scanner;
import com.google.gson.Gson;
import Dao.*;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws Exception {
        APIData data = new Gson().fromJson(TriviaAPI.get("amount=10", "type=multiple", "category=9"), APIData.class);
        Question[] questions = data.getQuestions();
        Scanner in = new Scanner(System.in);
        int ans;
        int correctAnswers = 0;
        int questionNumber = 1;
        System.out.println(data.getResponseCode());
        System.out.println();
        for (Question question : questions) {
            System.out.println(questionNumber + " - " + question.getQuestionTitle());
            System.out.println(question.toString(question.getAnswersSorted()));
            System.out.println();
            System.out.print("Please choose an answer (1-4): ");
            ans = in.nextInt();
            if (question.getAnswersSorted()[ans-1].equals(question.getCorrectAnswer())){
                System.out.println();
                System.out.println("Correct");
                correctAnswers = correctAnswers + 1;
            }else {
                System.out.println();
                System.out.println("Incorrect");
                System.out.println("Correct answer: " + question.getCorrectAnswer());
            }
            System.out.println();
            questionNumber++;
        }
        in.close();
        System.out.println("Correct answers: " + correctAnswers);
    }
}
