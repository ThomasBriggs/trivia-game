import java.util.Scanner;
import com.google.gson.Gson;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String json = Trivia.get("amount=3", "category=9", "difficulty=hard", "type=multiple");
        Question[] questions = new Gson().fromJson(json, APIData.class).getQuestions();
        Scanner in = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question);
            System.out.println();
            
        }
    }
}