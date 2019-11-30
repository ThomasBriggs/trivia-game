import java.util.Scanner;
import com.google.gson.Gson;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String json = Trivia.get("amount=20", "category=9", "difficulty=easy", "type=multiple");
        Question[] questions = new Gson().fromJson(json, APIData.class).getQuestions();
        Scanner in = new Scanner(System.in);
        int ans;
        int questionNumber = 1;
        for (Question question : questions) {
            System.out.println(questionNumber + "\t" + question.toString(question.getAnswersSorted()));
            System.out.println();
            System.out.print("Please choose an answer (1-4): ");
            ans = in.nextInt();
            if (question.getAnswersSorted()[ans-1].equals(question.getCorrect_answer())){
                System.out.println("Correct");
            }else {
                System.out.println("Incorrect");
                System.out.println("Correct answer: " + question.getCorrect_answer());
            }
            System.out.println();
            questionNumber++;
        }
        in.close();
    }
}
